package com.ani.sunny.api.commons.dto.device;

import com.ani.agent.service.commons.object.enumeration.DeviceState;

import java.io.Serializable;

/**
 * Created by zhanglina on 17-5-26.
 */
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = 315138556589693386L;
    public String identificationCode;
    public Long masterId;               //平台的master oject ID
    public Integer slaveId;             //平台的slave object ID
    public String name;                 //设备name
    public DeviceState deviceState;     //设备的状态：在线、离线
    public String deviceType;           //设备的类型：是灯 还是 空调
    public String deviceModel;          //设备的具体型号：厂家表示的型号
    public String logoUrl;              //设备logo的路由
    public Long ownerId;                //设备所有者ID
    public DeviceDto() {
    }

    public DeviceDto(Long masterId, Integer slaveId,
                     DeviceState deviceState,
                     String deviceType,
                     String deviceModel,
                     String identificationCode,
                     String name, Long ownerId, String logoUrl) {
        this.masterId = masterId;
        this.slaveId = slaveId;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.deviceModel = deviceModel;
        this.identificationCode = identificationCode;
        this.name = name;
        this.ownerId = ownerId;
        this.logoUrl = logoUrl;
    }
}
