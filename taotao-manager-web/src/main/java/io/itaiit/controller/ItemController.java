package io.itaiit.controller;

import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.pojo.TbItem;
import io.itaiit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){

        // 对参数进行非空判断

        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc) throws Exception {
        System.out.println("item.getPrice() = " + item.getPrice());
        TaotaoResult taotaoResult = itemService.saveItem(item, desc);

        // 此处添加生产者消息发送逻辑？？

        return taotaoResult;
    }
}
