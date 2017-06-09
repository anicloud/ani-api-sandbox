package com.ani.sunny.api.web.controller;

import com.ani.sunny.api.commons.dto.device.DeviceFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-5-26.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("testMaster")
    public ModelAndView testMasterDevice() {
        ModelAndView modelAndView = new ModelAndView("device");
//        DeviceFormDto formDto = new DeviceFormDto(1L,1L,"test","","test");
//        List<DeviceFormDto> formDtos = new ArrayList<DeviceFormDto>();
//        formDtos.add(formDto);
//        modelAndView.addObject("masters",formDtos);formDtos
        return modelAndView;
    }
}
