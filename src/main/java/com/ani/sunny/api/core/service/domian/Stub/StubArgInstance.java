package com.ani.sunny.api.core.service.domian.Stub;

import com.ani.sunny.api.commons.dto.stub.StubArgInstanceDto;
import com.ani.utils.core.datatype.AniDataType;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubArgInstance {
    public String name;
    public String value;
    public AniDataType aniDataType;

    public static List<StubArgInstance> fromDtoList(List<StubArgInstanceDto> stubArgInstanceDtos){
        List<StubArgInstance> stubArgInstances=new ArrayList<StubArgInstance>();
        if(stubArgInstanceDtos!=null && stubArgInstanceDtos.size()!=0){
            for (StubArgInstanceDto stubArgInstanceDto:stubArgInstanceDtos){
                StubArgInstance stubArgInstance=new StubArgInstance();
                stubArgInstance.name=stubArgInstanceDto.name;
                stubArgInstance.value=stubArgInstanceDto.value;
                stubArgInstance.aniDataType=stubArgInstanceDto.aniDataType;
            }
        }
        return null;
    }
}
