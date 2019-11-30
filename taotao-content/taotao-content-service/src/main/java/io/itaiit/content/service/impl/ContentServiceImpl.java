package io.itaiit.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.common.utils.JsonUtils;
import io.itaiit.content.service.ContentService;
import io.itaiit.mapper.TbContentMapper;
import io.itaiit.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisPool jedisPool;
    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public EasyUIDataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page, rows);
        List<TbContent> tbContents = tbContentMapper.selectByCategoryId(categoryId);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }

    @Override
    public TaotaoResult saveContent(TbContent content) {
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        tbContentMapper.insert(content);

        try {
            // 当添加内容时，删除缓存
            Jedis jedis = jedisPool.getResource();
            jedis.hdel(CONTENT_KEY, content.getCategoryId().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCategoryId(Long categoryId) {
        /*
         添加缓存的原则是不能影响正常业务
         */
        try {
            // 1. 先判断缓存中是否有对应的数据
            Jedis jedis = jedisPool.getResource();
            String content = jedis.hget(CONTENT_KEY, categoryId + "");
            if (StringUtils.isNotBlank(content)) {
                System.out.println("有缓存!!!");
                List<TbContent> tbContents = JsonUtils.jsonToList(content, TbContent.class);
                return tbContents;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        List<TbContent> tbContents = tbContentMapper.selectByCategoryId(categoryId);

        try {
            // 若缓存中没有对应的数据，则将查询出来的数据添加到缓存中
            System.out.println("没有缓存!!!");
            Jedis jedis = jedisPool.getResource();
            jedis.hset(CONTENT_KEY, categoryId + "", JsonUtils.objectToJson(tbContents));
        } catch (Exception e){
            e.printStackTrace();
        }
        return tbContents;
    }
}
