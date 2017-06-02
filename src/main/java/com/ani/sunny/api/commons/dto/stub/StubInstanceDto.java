package com.ani.sunny.api.commons.dto.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubInstanceDto {
    public Integer stubId;
    public String name;
    public Long groupId;
    public Long userId;
    public Long masterId;
    public Integer deviceId;
    public List<StubArgInstanceDto> inputList;
    public static StubInstanceDto fetchAniStub(AniStub aniStub){
        StubInstanceDto stubInstanceDto=new StubInstanceDto();
        stubInstanceDto.groupId=aniStub.stubGroupId;
        stubInstanceDto.stubId=aniStub.stubId;
        stubInstanceDto.masterId=aniStub.targetObjectId;
        stubInstanceDto.deviceId=aniStub.targetSlaveId;
        List<StubArgInstanceDto> list=new ArrayList<>();
        for(com.ani.octopus.commons.stub.dto.StubArgumentDto stubArgumentDto:aniStub.inputArguments){
            StubArgInstanceDto stubArgInstanceDto=StubArgInstanceDto.fetchStubArgumentDto(stubArgumentDto);
            list.add(stubArgInstanceDto);
        }
        stubInstanceDto.inputList=list;
        stubInstanceDto.userId=aniStub.accountId;
 //       stubInstanceDto.inputList=aniStub.inputArguments;
       // stubInstanceDto.groupId=
        return stubInstanceDto;

    }


}
