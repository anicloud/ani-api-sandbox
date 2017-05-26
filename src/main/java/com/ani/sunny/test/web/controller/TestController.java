package com.ani.sunny.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihui on 17-5-26.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "helloworld";
    }
}
