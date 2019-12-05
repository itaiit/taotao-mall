package io.itaiit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.common.utils.IDUtils;
import io.itaiit.mapper.TbItemDescMapper;
import io.itaiit.mapper.TbItemMapper;
import io.itaiit.pojo.TbItem;
import io.itaiit.pojo.TbItemDesc;
import io.itaiit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItem> tbItems = tbItemMapper.selectAll();
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(tbItems);
        return result;
    }

    @Override
    public TaotaoResult saveItem(TbItem item, String desc) throws Exception {
        long id = IDUtils.genItemId();
        item.setId(id);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        item.setStatus((byte) 1);
        tbItemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        tbItemDescMapper.insert(itemDesc);

        /*
        添加消息队列，activemq，作为生产者
        消息内容：为SearchItem的序列化内容，发送到消息队列中

        持久化机制，采用jdbc persist + journal
         */
        // 发送消息到消息队列
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(item.getId().toString());
                return textMessage;
            }
        });
        return TaotaoResult.ok();
    }
}
