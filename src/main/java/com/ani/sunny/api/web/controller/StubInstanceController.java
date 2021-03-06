package com.ani.sunny.api.web.controller;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.octopus.commons.stub.dto.StubArgumentInfoDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.dto.device.DeviceFormDto;
import com.ani.sunny.api.commons.dto.stub.StubArgInstanceDto;
import com.ani.sunny.api.commons.dto.stub.StubArgumentDto;
import com.ani.sunny.api.commons.dto.stub.StubDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.commons.dto.user.UserDto;
import com.ani.sunny.api.core.service.service.file.FileInfoService;
import com.ani.sunny.api.core.service.service.stub.StubInstanceService;
import com.ani.sunny.api.thread.PortLisenter;
import com.ani.sunny.api.thread.PortTcpLisenter;
import com.ani.utils.network.IpAddress;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-5-31.
 */
@Controller
@RequestMapping("/stub")
public class StubInstanceController {
    @Resource
    StubInstanceService stubInstanceService;
    @Resource
    FileInfoService fileInfoService;
    @RequestMapping("/invoke/{masterId}/{slaveId}/{groupId}/{stubId}")
    public ModelAndView invokeStub(HttpServletRequest request, @PathVariable Long masterId,@PathVariable Integer slaveId,@PathVariable Long groupId,@PathVariable Integer stubId){
        ModelAndView  message =new ModelAndView("device");
        HttpSession session = request.getSession();
        Long hashUserId = (Long) session.getAttribute(Constants.SUNNY_HASH_USER_ID_SESSION);
        StubInstanceDto stubInstanceDto =null;
        List<DeviceFormDto> deviceFormDtos =new ArrayList<DeviceFormDto>();
        List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList=Constants.DEVICE_MASTER_MAPPINGS.get(hashUserId);
        for (DeviceMasterObjInfoDto deviceMasterObjInfoDto:deviceMasterObjInfoDtoList){
            DeviceFormDto deviceFormDto=DeviceFormDto.fetchDeviceMasterObjInfoDto(deviceMasterObjInfoDto);
            deviceFormDtos.add(deviceFormDto);
        }
        List<StubInfoDto> stubInfoDtoList=null;
        if (slaveId==-1){
            stubInfoDtoList =Constants.MASTER_STUB_MAPPING.get(hashUserId+":"+masterId);
        }else {
            stubInfoDtoList =Constants.SLAVE_STUB_MAPPINGS.get(masterId+":"+slaveId);


        }
        List<StubDto> stubDtos=new ArrayList<StubDto>();
        for (StubInfoDto stubInfoDto:stubInfoDtoList){
             stubDtos.add(StubDto.fetchStubInfoDto(stubInfoDto));
            if(stubId.equals(stubInfoDto.stubId) && groupId.equals(stubInfoDto.group.groupId) ){
                stubInstanceDto=new StubInstanceDto();
                stubInstanceDto.groupId=stubInfoDto.group.groupId;
                stubInstanceDto.stubId=stubInfoDto.stubId;
                stubInstanceDto.userId=hashUserId;
                stubInstanceDto.masterId=masterId;
                stubInstanceDto.deviceId=slaveId;
                List<StubArgInstanceDto> stubArgInstanceDtos=new ArrayList<>();
                int i=0;
                for(StubArgumentInfoDto stubArgumentInfoDto:stubInfoDto.inputArguments){
                    StubArgInstanceDto stubArgInstanceDto=new StubArgInstanceDto();
                    stubArgInstanceDto.name=stubArgumentInfoDto.name;
                    stubArgInstanceDto.aniDataType=stubArgumentInfoDto.dataType;
                    stubArgInstanceDto.value=request.getParameter("value"+i);
                    stubArgInstanceDtos.add(stubArgInstanceDto);
                    i++;
                }
                stubInstanceDto.inputList=stubArgInstanceDtos;

            }
        }
        try {
            if (stubInstanceDto!=null){

                if (stubInstanceDto.groupId==13 && stubInstanceDto.stubId==309){
                    stubInstanceDto.inputList.get(2).value=IpAddress.getIpAddress();
                    PortLisenter portLisenter=new PortLisenter(Integer.parseInt(stubInstanceDto.inputList.get(3).value));
                    Thread thread = new Thread(portLisenter);
                    thread.start();
                }

                if (stubInstanceDto.groupId==13 && stubInstanceDto.stubId==311){
                        stubInstanceDto.inputList.get(2).value=IpAddress.getIpAddress();
                        PortTcpLisenter portLisenter=new PortTcpLisenter(Integer.parseInt(stubInstanceDto.inputList.get(3).value));
                        Thread thread = new Thread(portLisenter);
                        thread.start();
                }

                Object result = stubInstanceService.invokeStubInstance(stubInstanceDto);

                if (stubInstanceDto.groupId==13 && stubInstanceDto.stubId==301){
                    System.out.println(result);
                    ModelAndView message1=new ModelAndView("image");

                    message1.addObject("values",result);
                    return message1;

                }
                ModelAndView message1=new ModelAndView("argument");
                message1.addObject("values",result);
                return message1;


            }

        }catch (Exception e){
             DeviceFormDto deviceFormDto=new DeviceFormDto();
            deviceFormDto.setName("验证失败");
            List<DeviceFormDto> stubDtos2=new ArrayList<>();
            stubDtos2.add(deviceFormDto);
            message.addObject("masters",stubDtos2);

        }


        return message;
    }
}
