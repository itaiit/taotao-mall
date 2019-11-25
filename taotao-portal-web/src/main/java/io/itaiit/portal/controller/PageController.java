package io.itaiit.portal.controller;

import io.itaiit.common.utils.JsonUtils;
import io.itaiit.content.service.ContentService;
import io.itaiit.pojo.TbContent;
import io.itaiit.portal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;

    @RequestMapping("/index")
    public String ShowIndex(Model mode) {
        List<TbContent> contents = contentService.getContentListByCategoryId(AD1_CATEGORY_ID);
        List<Ad1Node> ad1Nodes = new ArrayList<>();
        for (TbContent content : contents
        ) {
            Ad1Node node = new Ad1Node();
            node.setAlt(content.getSubTitle());
            node.setHref(content.getUrl());
            node.setSrc(content.getPic());
            node.setSrcB(content.getPic2());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            ad1Nodes.add(node);
        }
        mode.addAttribute("ad1", JsonUtils.objectToJson(ad1Nodes));
        return "index";
    }
}
