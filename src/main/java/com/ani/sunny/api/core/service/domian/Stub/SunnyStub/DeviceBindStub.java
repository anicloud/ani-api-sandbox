package com.ani.sunny.api.core.service.domian.Stub.SunnyStub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.sunny.api.commons.stub.SunnyStub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-7-14.
 */
public class DeviceBindStub implements SunnyStub {
    @Override
    public List<StubArgumentDto> invokeStub(AniStub stub) {
       List<StubArgumentDto> stubArgumentDtos=new ArrayList<>();
       StubArgumentDto stubArgumentDto=new StubArgumentDto();
       stubArgumentDto.value="hello";
       return stubArgumentDtos;
    }

}
