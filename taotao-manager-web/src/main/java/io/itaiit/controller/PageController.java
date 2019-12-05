package io.itaiit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 显示商品的查询页面
     * url:/item-list
     * 由于注意到请求的uri和页面名称相同，因此可以使用url模板映射
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable("page") String page) {
        return page;
    }

}
