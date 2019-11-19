package io.itaiit.controller;

import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){

        // 对参数进行非空判断

        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}
