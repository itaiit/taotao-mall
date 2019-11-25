package io.itaiit.controller;

import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.content.service.ContentService;
import io.itaiit.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {

    // content service
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/content/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getContentList(Integer page, Integer rows, Long categoryId){
        //1. 从service中获得结果集，参数为categoryId
        return contentService.getContentList(page, rows, categoryId);
    }

    @RequestMapping("//content/save")
    public TaotaoResult saveContent(TbContent content){
        return contentService.saveContent(content);
    }
}
