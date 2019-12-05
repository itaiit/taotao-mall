package io.itaiit.service;

import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.pojo.TbItem;

public interface ItemService {

    EasyUIDataGridResult getItemList(Integer page, Integer rows);

    TaotaoResult saveItem(TbItem item, String desc) throws Exception;

}
