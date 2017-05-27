package com.ani.sunny.api.web.controller;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.octopus.commons.object.enumeration.AniObjectState;
import com.ani.sunny.api.commons.dto.device.DeviceMasterFormDto;
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
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "helloworld";
    }

    @RequestMapping("testMaster")
    public ModelAndView testMasterDevice() {
        ModelAndView modelAndView = new ModelAndView("device");
        DeviceMasterFormDto formDto = new DeviceMasterFormDto(1L,1,"test","test","test",null,null);
        List<DeviceMasterFormDto> formDtos = new ArrayList<DeviceMasterFormDto>();
        formDtos.add(formDto);
        modelAndView.addObject("masters",formDtos);
        return modelAndView;
    }
}
