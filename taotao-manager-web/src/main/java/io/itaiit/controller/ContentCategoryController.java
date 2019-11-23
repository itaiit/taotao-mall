package io.itaiit.controller;

import io.itaiit.common.pojo.EasyUITreeNode;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/content/category/list", method = RequestMethod.GET)
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        List<EasyUITreeNode> contentCategoryList = contentCategoryService.getContentCategoryList(parentId);
        return contentCategoryList;
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult saveContentCategory(Long parentId, String name){
        TaotaoResult result = contentCategoryService.saveContentCategory(parentId, name);
        return result;
    }
}
