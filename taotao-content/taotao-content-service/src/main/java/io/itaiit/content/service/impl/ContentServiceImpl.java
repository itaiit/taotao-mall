package io.itaiit.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.content.service.ContentService;
import io.itaiit.mapper.TbContentMapper;
import io.itaiit.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

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
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCategoryId(Long categoryId) {
        List<TbContent> tbContents = tbContentMapper.selectByCategoryId(categoryId);
        return tbContents;
    }
}
