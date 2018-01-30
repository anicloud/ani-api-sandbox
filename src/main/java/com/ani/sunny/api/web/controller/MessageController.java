package com.ani.sunny.api.web.controller;

import com.ani.sunny.api.commons.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhanglina on 17-9-26.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @RequestMapping("/show")
    public ModelAndView refureshMessage(HttpServletRequest request){
     //   Constants.messageList.add("hello");
        List<String> msglist=Constants.messageList;
        ModelAndView message=new ModelAndView("message");
        message.addObject("messages",msglist);
        return message;
    }
}
