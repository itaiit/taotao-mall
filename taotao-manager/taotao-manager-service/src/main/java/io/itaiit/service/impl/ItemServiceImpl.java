package io.itaiit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.mapper.TbItemMapper;
import io.itaiit.pojo.TbItem;
import io.itaiit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

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
}
