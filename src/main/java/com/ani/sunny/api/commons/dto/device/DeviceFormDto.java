package com.ani.sunny.api.commons.dto.device;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanglina on 17-5-26.
 */
public class DeviceFormDto implements Serializable {
    private static final long serialVersionUID = 7219232880181839661L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    private Long masterId;
    private Long deviceId;
    private String name;
    private String description;
    private String deviceState;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceFormDto(){}
    public DeviceFormDto(Long masterId,Long deviceId,String name,String description,String deviceState){
        this.masterId=masterId;
        this.deviceId=deviceId;
        this.name=name;
        this.description=description;
        this.deviceState=deviceState;
    }

    public  static DeviceFormDto fetchDeviceMasterObjInfoDto(DeviceMasterObjInfoDto deviceMasterObjInfoDto){
        DeviceFormDto deviceFormDto=new DeviceFormDto();
        deviceFormDto.masterId=(long)-1;
        deviceFormDto.deviceId=deviceMasterObjInfoDto.objectId;
        deviceFormDto.name=deviceMasterObjInfoDto.name;
        deviceFormDto.description=deviceMasterObjInfoDto.description;
        deviceFormDto.deviceState=deviceMasterObjInfoDto.state.toString();
        return deviceFormDto;
    }
    public static DeviceFormDto fetchDeviceSlavaObjInfoDto(DeviceSlaveObjInfoDto deviceSlaveObjInfoDto){
        DeviceFormDto deviceFormDto=new DeviceFormDto();
        deviceFormDto.deviceId=deviceSlaveObjInfoDto.objectSlaveId.longValue();
        deviceFormDto.name=deviceSlaveObjInfoDto.name;
        deviceFormDto.description=deviceSlaveObjInfoDto.description;
        if (deviceSlaveObjInfoDto.state!=null){
            deviceFormDto.deviceState=deviceSlaveObjInfoDto.state.toString();
        }
       //
        return deviceFormDto;
    }

}
