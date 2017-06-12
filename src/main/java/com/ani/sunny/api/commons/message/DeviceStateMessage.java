package com.ani.sunny.api.commons.message;

import com.ani.bus.service.commons.message.callmessage.AniStateObjectMessage;
import com.ani.octopus.commons.state.dto.StateTransDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-6-11.
 */
public class DeviceStateMessage {
    public Long deviceId;
    public Integer slaveId;
    public List<BoxStateTransDto> boxStateTransDtos;
    public static DeviceStateMessage fetchAniStateObjectMessage(AniStateObjectMessage aniStateObjectMessage){
        DeviceStateMessage deviceStateMessage=new DeviceStateMessage();
        deviceStateMessage.deviceId=aniStateObjectMessage.deviceId;
        deviceStateMessage.slaveId=aniStateObjectMessage.slaveId;
        List<BoxStateTransDto> boxStateTransDtos=new ArrayList();
        for(StateTransDto stateTransDto:aniStateObjectMessage.stateTransDtos) {
            boxStateTransDtos.add(BoxStateTransDto.fetchStateTransDto(stateTransDto));
        }
        deviceStateMessage.boxStateTransDtos=boxStateTransDtos;
        return  deviceStateMessage;
    }

}
