package com.ani.sunny.api.web.controller;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.aniservice.AniServiceObjInfoDto;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.dto.device.DeviceFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-7-25.
 */
@Controller
@RequestMapping("test")
public class InvokeStubTest {
    @Resource
    AgentTemplate agentTemplate;

    @RequestMapping("invoke")
    public void invokeStubtest(){
        try {
        List<AniServiceObjInfoDto> aniServiceObjInfoDtos=agentTemplate.getServiceObjService(Constants.aniOAuthAccessToken.toString())
                .getAccessableSerObj(Constants.userId);
        AniServiceObjInfoDto aniServiceObjInfoDto=aniServiceObjInfoDtos.get(1);

        List<StubInfoDto> stubInfoDtos= aniServiceObjInfoDto.stubInfoDtos;
        if(stubInfoDtos!=null &&stubInfoDtos.size()!=0){
            StubInfoDto stubInfoDto=stubInfoDtos.get(2);
            StubInfoDto stubInfoDto1=stubInfoDtos.get(0);
            StubArgumentDto stubArgumentDto=new StubArgumentDto(1);
            List<StubArgumentDto> stubArgumentDtos=new ArrayList<>();
            stubArgumentDtos.add(stubArgumentDto);
            StubArgumentDto stubArgumentDto1=new StubArgumentDto(1.0f);
            List<StubArgumentDto> stubArgumentDtos1=new ArrayList<>();
            stubArgumentDtos1.add(stubArgumentDto1);

            //for(int i=0;i<10;i++) {
                AniStub aniStub=new AniStub(
                        aniServiceObjInfoDto.objectId,
                        aniServiceObjInfoDto.accountId,
                        stubInfoDto.group.groupId,
                        stubInfoDto.stubId,
                        stubArgumentDtos
                );
              AniStub aniStub1=  agentTemplate.getAniInvokable(Constants.ANI_SERVICE_SESSION).invokeAniObjectSync(aniStub);
              System.out.println(aniStub1);
                AniStub aniStubs=new AniStub(
                        aniServiceObjInfoDto.objectId,
                        aniServiceObjInfoDto.accountId,
                        stubInfoDto1.group.groupId,
                        stubInfoDto1.stubId,
                        stubArgumentDtos1
                );
              AniStub aniStub2=agentTemplate.getAniInvokable(Constants.ANI_SERVICE_SESSION).invokeAniObjectSync(aniStubs);
              System.out.println(aniStub2+"anistub2");
             // aniStub.outputArguments=null;
            }
     //   }
        for (int i=0;i<10;i++){
            System.out.println(i);
        }
        //}
        } catch (Exception e) {
            e.printStackTrace();

            // modelAndView.addObject("error","拉取设备失败");
        }
    }

}
