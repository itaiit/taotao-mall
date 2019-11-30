package io.itaiit.search.controller;

import io.itaiit.common.pojo.SearchResult;
import io.itaiit.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public ModelAndView search(Integer page, @RequestParam("q") String queryString) throws Exception {
        queryString = new String(queryString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        SearchResult search = searchService.search(queryString, page, 60);
        ModelAndView mv = new ModelAndView();
        mv.addObject("query", queryString);
        mv.addObject("totalPages", search.getPageCount());
        mv.addObject("itemList", search.getItemList());
        mv.addObject("page", page);
        mv.setViewName("search");
        return mv;
    }
}
