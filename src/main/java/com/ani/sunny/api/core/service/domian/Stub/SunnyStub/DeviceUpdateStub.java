package com.ani.sunny.api.core.service.domian.Stub.SunnyStub;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.message.callmessage.AniStateObjectMessage;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.sunny.api.commons.stub.SunnyStub;

import java.util.List;

/**
 * Created by zhanglina on 17-7-14.
 */
public class DeviceUpdateStub implements SunnyStub {
    @Override
    public List<StubArgumentDto> invokeStub(AniStub stub) {
        List<StubArgumentDto> stubArgumentDtos=stub.inputArguments;
        for (StubArgumentDto stubArgumentDto:stubArgumentDtos){
            DeviceMasterObjInfoDto deviceMasterObjInfoDto=(DeviceMasterObjInfoDto)stubArgumentDto.value;
            System.out.println(deviceMasterObjInfoDto);
            //AniStateObjectMessage
        }

        return null;
    }
}
