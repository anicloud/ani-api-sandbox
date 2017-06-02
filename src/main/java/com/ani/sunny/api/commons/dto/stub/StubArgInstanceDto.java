package com.ani.sunny.api.commons.dto.stub;
import com.ani.octopus.commons.stub.dto.*;
import com.ani.utils.core.datatype.AniDataType;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubArgInstanceDto { public String name;
    public String value;
    public AniDataType aniDataType;
    public static StubArgInstanceDto fetchStubArgumentDto(com.ani.octopus.commons.stub.dto.StubArgumentDto stubArgumentDto){
        StubArgInstanceDto stubArgInstanceDto=new StubArgInstanceDto();
        stubArgInstanceDto.value=stubArgumentDto.value.toString();
        return stubArgInstanceDto;
    }


}
