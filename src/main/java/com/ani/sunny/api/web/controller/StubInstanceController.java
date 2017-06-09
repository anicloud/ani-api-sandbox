package com.ani.sunny.api.web.controller;

import com.ani.octopus.commons.stub.dto.StubArgumentInfoDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.dto.stub.StubArgInstanceDto;
import com.ani.sunny.api.commons.dto.stub.StubDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.commons.dto.user.UserDto;
import com.ani.sunny.api.core.service.service.stub.StubInstanceService;
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
    @RequestMapping("/invoke/{masterId}/{slaveId}/{groupId}/{stubId}")
    public ModelAndView invokeStub(HttpServletRequest request, @PathVariable Long masterId,@PathVariable Integer slaveId,@PathVariable Long groupId,@PathVariable Integer stubId){
        ModelAndView  message =new ModelAndView("stub");
        HttpSession session = request.getSession();
        Long hashUserId = (Long) session.getAttribute(Constants.SUNNY_HASH_USER_ID_SESSION);
        StubInstanceDto stubInstanceDto =null;
        List<StubInfoDto> stubInfoDtoList=Constants.SLAVE_STUB_MAPPINGS.get(masterId+":"+slaveId);
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
                boolean result = stubInstanceService.invokeStubInstance(stubInstanceDto);
                if(result){
                    message.addObject("stubs",stubDtos);

                }else {
                    StubDto stubDto=new StubDto();
                    stubDto.setName("invoke failed");
                    List<StubDto> stubDtos1=new ArrayList<>();
                    stubDtos1.add(stubDto);
                    message.addObject("stubs",stubDtos1);
                }
            }

        }catch (Exception e){
            StubDto stubDto=new StubDto();
            stubDto.setName("param invalid");
            List<StubDto> stubDtos1=new ArrayList<>();
            stubDtos1.add(stubDto);
            message.addObject("stubs",stubDtos1);

        }


        return message;
    }
}
