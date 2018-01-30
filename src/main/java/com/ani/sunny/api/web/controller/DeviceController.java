package com.ani.sunny.api.web.controller;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.websocket.StubInvokeListener;
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

import javax.jws.WebParam;
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

    @RequestMapping("/slave/{objectId}")
    public ModelAndView getSlaveDevices(HttpServletRequest request, @PathVariable Long objectId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView modelAndView = new ModelAndView("slave");
        List<DeviceFormDto> deviceFormDtos=new ArrayList<DeviceFormDto>();
        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            boolean flag = false;
            if(Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId) != null)
            for(DeviceMasterObjInfoDto masterObjInfoDto:Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId)) {
                Long a=masterObjInfoDto.objectId;
                if(a.equals(objectId)) {
                    flag = true;
                    if (Constants.SLAVE_OBJ_INFO_DTO_MAP.get(accountDto.accountId+":"+objectId)==null){
                        Constants.SLAVE_OBJ_INFO_DTO_MAP.put(accountDto.accountId+":"+objectId,masterObjInfoDto.slaves);
                    }

                    List<DeviceSlaveObjInfoDto> deviceSlaveObjInfoDtos=Constants.SLAVE_OBJ_INFO_DTO_MAP.get(accountDto.accountId+":"+objectId);
                   // List<DeviceFormDto> deviceFormDtos=new ArrayList<DeviceFormDto>();
                    for (DeviceSlaveObjInfoDto deviceSlaveObjInfoDto:deviceSlaveObjInfoDtos){
                        DeviceFormDto deviceFormDto=DeviceFormDto.fetchDeviceSlavaObjInfoDto(deviceSlaveObjInfoDto);
                        deviceFormDto.setMasterId(objectId);
                        deviceFormDtos.add(deviceFormDto);
                    }

                    modelAndView.addObject("slaves",deviceFormDtos);
                    break;
                }
            }
            if(!flag) {
                //为了方便前台展示这样写
                  DeviceFormDto deviceFormDto=new DeviceFormDto();
                  deviceFormDto.setName("主设备不存在");

            }
        } else {
            DeviceFormDto deviceFormDto=new DeviceFormDto();
            deviceFormDto.setName("用户未登陆或token过期");
        }
        modelAndView.addObject("slaves",deviceFormDtos);
        return modelAndView;
    }
    @RequestMapping("/stub/{objectId}")
    public ModelAndView getMasterStubs(HttpServletRequest request, @PathVariable Long objectId){
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView modelAndView=new ModelAndView("stub");
        //ModelAndView result = new ModelAndView("stub");
        List<StubDto> masterStubs=new ArrayList<StubDto>();
        if (!StringUtils.isEmpty(accessToken)){
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            boolean flag = false;
            if(Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId) != null)
                for(DeviceMasterObjInfoDto masterObjInfoDto:Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId)) {
                    Long a=masterObjInfoDto.objectId;
                    if(a.equals(objectId)) {
                        flag = true;
                        if (Constants.MASTER_STUB_MAPPING.get(accountDto.accountId+":"+objectId)==null){
                            Constants.MASTER_STUB_MAPPING.put(accountDto.accountId+":"+objectId,masterObjInfoDto.stubs);
                        }

                        List<StubInfoDto> stubInfoDtos=Constants.MASTER_STUB_MAPPING.get(accountDto.accountId+":"+objectId);
                        // List<DeviceFormDto> deviceFormDtos=new ArrayList<DeviceFormDto>();
                        for (StubInfoDto stubInfoDto:stubInfoDtos){
                            StubDto stubDto=StubDto.fetchStubInfoDto(stubInfoDto);
                            stubDto.setMasterId(objectId);
                            stubDto.setSlaveId(-1);
                            masterStubs.add(stubDto);
                        }
                        modelAndView.addObject("stubs",masterStubs);
                        break;
                    }
                }
            if(!flag) {
                //为了方便前台展示这样写
                DeviceFormDto deviceFormDto=new DeviceFormDto();
                deviceFormDto.setName("主设备不存在");


            }
        }
        return modelAndView;
    }

    @RequestMapping("/slave/stubs/{masterId}/{slaveId}")

    public ModelAndView getSlaveStubs(HttpServletRequest request,@PathVariable Long masterId,@PathVariable Integer slaveId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView result = new ModelAndView("stub");
        List<StubDto> stubs=new ArrayList<StubDto>();
        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            if(Constants.SLAVE_OBJ_INFO_DTO_MAP !=null && accountDto != null) {
                List<DeviceSlaveObjInfoDto> slaveObjInfoDtos = Constants.SLAVE_OBJ_INFO_DTO_MAP.get(accountDto.accountId + ":" + masterId);
                if (slaveObjInfoDtos != null) {
                    boolean flag = false;
                    for (DeviceSlaveObjInfoDto slaveObjInfoDto : slaveObjInfoDtos) {
                        if (slaveObjInfoDto.objectSlaveId.equals(slaveId)) {
                            flag = true;
                            for (StubInfoDto stubInfoDto:slaveObjInfoDto.stubs){
                                StubDto stubDto=StubDto.fetchStubInfoDto(stubInfoDto);
                                stubDto.setMasterId(masterId);
                                stubDto.setSlaveId(slaveId);
                                stubs.add(stubDto);
                            }
                            Constants.SLAVE_STUB_MAPPINGS.put(masterId + ":" + slaveId, slaveObjInfoDto.stubs);
                        }
                    }
                    if (!flag) {

                        StubDto stubDto=new StubDto();
                        stubDto.setName("从设备不存在");
                        stubs.add(stubDto);
                        result.addObject("description", "从设备不存在");
                    }
                } else {
                    StubDto stubDto=new StubDto();
                    stubDto.setName("从设备不存在");
                    stubs.add(stubDto);

                }
            } else {
                StubDto stubDto=new StubDto();
                stubDto.setName("从设备不存在");
                stubs.add(stubDto);

            }
        } else {
            StubDto stubDto=new StubDto();
            stubDto.setName("用户未登陆或token过期");
            stubs.add(stubDto);
        }
        result.addObject("stubs",stubs);
        return result;
    }

}
