package io.itaiit.controller;

import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImportAllItems {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/index/importAll", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult importAll() throws Exception {
        TaotaoResult result = searchService.importAllSearchItems();
        return result;
    }
}
