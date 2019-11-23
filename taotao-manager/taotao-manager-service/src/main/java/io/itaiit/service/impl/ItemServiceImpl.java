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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

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
    public TaotaoResult saveItem(TbItem item, String desc) {
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
        return TaotaoResult.ok();
    }
}
