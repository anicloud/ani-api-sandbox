package com.ani.sunny.api.web.controller;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.dto.device.DeviceFormDto;
import com.ani.sunny.api.commons.dto.stub.StubArgumentDto;
import com.ani.sunny.api.commons.dto.stub.StubDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.rmi.CORBA.Stub;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-5-27.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private AgentTemplate agentTemplate;
    @RequestMapping("/master")
    public ModelAndView getMasters(){
        ModelAndView modelAndView=new ModelAndView("device");
        List<DeviceFormDto> deviceFormDtos =new ArrayList<DeviceFormDto>();
        DeviceFormDto deviceFormDto=new DeviceFormDto();
        deviceFormDto.setName("a");

        deviceFormDto.setMasterId(-1L);
        deviceFormDto.setDeviceId(123L);
        deviceFormDto.setDeviceState("activity");
        deviceFormDtos.add(deviceFormDto);
        modelAndView.addObject("masters",deviceFormDtos);
        return modelAndView;
    }
    @RequestMapping("/slave/{objectId}")
    public ModelAndView getSlaveDevices(HttpServletRequest request, @PathVariable Long objectId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView modelAndView = new ModelAndView("slave");

        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            boolean flag = false;
            if(Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId) != null)
            for(DeviceMasterObjInfoDto masterObjInfoDto:Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId)) {
                Long a=masterObjInfoDto.objectId;
                if(a.equals(objectId)) {
                    flag = true;
                    Constants.SLAVE_OBJ_INFO_DTO_MAP.put(accountDto.accountId+":"+objectId,masterObjInfoDto.slaves);
                    List<DeviceSlaveObjInfoDto> deviceSlaveObjInfoDtos=masterObjInfoDto.slaves;
                    List<DeviceFormDto> deviceFormDtos=new ArrayList<DeviceFormDto>();
                    for (DeviceSlaveObjInfoDto deviceSlaveObjInfoDto:deviceSlaveObjInfoDtos){
                        DeviceFormDto deviceFormDto=DeviceFormDto.fetchDeviceSlavaObjInfoDto(deviceSlaveObjInfoDto);
                        deviceFormDtos.add(deviceFormDto);
                    }

                    modelAndView.addObject("slaves",deviceFormDtos);
                    modelAndView.addObject("masterId",masterObjInfoDto.objectId);

                    break;
                }
            }
            if(!flag) {
                  modelAndView.addObject("result","error");
                  modelAndView.addObject("massage","主设备不存在");

            }
        } else {
            modelAndView.addObject("result","error");
            modelAndView.addObject("massage","主设备不存在");
        }

        return modelAndView;
    }
    @RequestMapping("/slave/stubs/{masterId}/{slaveId}")

    public ModelAndView getSlaveStubs(HttpServletRequest request,@PathVariable Long masterId,@PathVariable Integer slaveId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView result = new ModelAndView("stub");
        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            if(Constants.SLAVE_OBJ_INFO_DTO_MAP !=null && accountDto != null) {
                List<DeviceSlaveObjInfoDto> slaveObjInfoDtos = Constants.SLAVE_OBJ_INFO_DTO_MAP.get(accountDto.accountId + ":" + masterId);
                if (slaveObjInfoDtos != null) {
                    boolean flag = false;
                    for (DeviceSlaveObjInfoDto slaveObjInfoDto : slaveObjInfoDtos) {
                        if (slaveObjInfoDto.objectSlaveId.equals(slaveId)) {
                            flag = true;
                            result.addObject("result", "success");
                            List<StubDto> stubs=new ArrayList<StubDto>();
                            for (StubInfoDto stubInfoDto:slaveObjInfoDto.stubs){
                                StubDto stubDto=StubDto.fetchStubInfoDto(stubInfoDto);
                                stubs.add(stubDto);
                            }
                            result.addObject("stubs", stubs);
                            Constants.SLAVE_STUB_MAPPINGS.put(masterId + ":" + slaveId, slaveObjInfoDto.stubs);
                        }
                    }
                    if (!flag) {

                        result.addObject("result", "error");
                        result.addObject("description", "从设备不存在");
                    }
                } else {
                    result.addObject("result", "error");
                    result.addObject("description", "对应主设备不存在");
                }
            } else {
                result.addObject("result","error");
                result.addObject("description","没找到从设备");
            }
        } else {
            result.addObject("result","error");
            result.addObject("description","用户未登陆或token过期");
        }

        return result;
    }
//    @RequestMapping("/argument/{masterId}/{slaveId}/{stubId}")
//    public ModelAndView getStubArguments(HttpServletRequest request,@PathVariable Long masterId,@PathVariable Integer slaveId,@PathVariable Integer stubId) {
//        ModelAndView modelAndView=new ModelAndView("stubargument");
//        String s=request.getParameter("value");
//        String s1=request.getParameter("value");
//        List<StubInfoDto> stubInfoDtos=Constants.SLAVE_STUB_MAPPINGS.get(masterId+":"+slaveId);
//        if(stubInfoDtos!=null &&stubInfoDtos.size()!=0){
//            for (StubInfoDto stubInfoDto:stubInfoDtos){
//               // Constants.STUB_ARGUMENTS.put(stubInfoDto.stubId,stubInfoDto.inputArguments);
//                if (stubId.equals(stubInfoDto.stubId)){
//                    List<StubArgumentDto> stubArgumentDtos=StubArgumentDto.fetchStubArgumentInfoDtos(stubInfoDto.inputArguments);
//                    modelAndView.addObject("arguments",stubArgumentDtos);
//                    break;
//                }
//            }
//
//        }
//        else {
//            modelAndView.addObject("error","获取stub列表失败");
//        }
//
//
//        return modelAndView;
//    }

}
