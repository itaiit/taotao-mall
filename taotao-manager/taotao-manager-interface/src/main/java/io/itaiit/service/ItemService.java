package io.itaiit.service;

import io.itaiit.common.pojo.EasyUIDataGridResult;

public interface ItemService {

    EasyUIDataGridResult getItemList(Integer page, Integer rows);

}
