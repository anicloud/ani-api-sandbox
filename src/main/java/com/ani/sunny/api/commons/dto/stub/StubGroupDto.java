package com.ani.sunny.api.commons.dto.stub;

import com.ani.octopus.commons.stub.domain.StubGroup;
import com.ani.octopus.commons.stub.dto.StubGroupInfoDto;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubGroupDto {
    public Long groupId;
    public String name;
    public StubGroupDto(){};
    public StubGroupDto(Long groupId,String name){
        this.groupId=groupId;
        this.name=name;
    }
    public static StubGroupDto fetchStubGoupInfoDto(StubGroupInfoDto stubGroupInfoDto){
        StubGroupDto stubGroupDto=new StubGroupDto();
        stubGroupDto.groupId=stubGroupInfoDto.groupId;
        stubGroupDto.name=stubGroupInfoDto.name;
        return  stubGroupDto;
    }
}
