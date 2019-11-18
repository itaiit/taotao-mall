package io.itaiit.controller;

import io.itaiit.service.TestMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author itaiit
 * @Date 2019/11/17 21:30
 * @Version 1.0
 */
@Controller
public class TestController {

    @Autowired
    private TestMapperService testMapperService;

    @RequestMapping("/gettime")
    @ResponseBody
    public String getTime(){
        return testMapperService.getTime();
    }
}
