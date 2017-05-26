package com.ani.sunny.test.commons.dto.device;

import java.io.Serializable;

/**
 * Created by zhanglina on 17-5-26.
 */
public class DeviceSlaveFormDto implements Serializable {
    private static final long serialVersionUID = 7219232880181839661L;

    private Long masterId;
    private Integer slaveId;
    private String name;
    private String deviceState;
    private String deviceType;
    private String deviceGroup;

    public DeviceSlaveFormDto() {
    }

    public DeviceSlaveFormDto( Long masterId, Integer slaveId, String name, String deviceState, String deviceType, String deviceGroup) {

        this.masterId = masterId;
        this.slaveId = slaveId;
        this.name = name;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.deviceGroup = deviceGroup;
    }

}
