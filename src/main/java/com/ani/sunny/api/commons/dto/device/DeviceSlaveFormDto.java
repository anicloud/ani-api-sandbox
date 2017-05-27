package com.ani.sunny.api.commons.dto.device;

import com.ani.sunny.api.commons.dto.function.FunctionDto;

import java.io.Serializable;
import java.util.List;

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
    private List<FunctionDto> functionDtos;
    public DeviceSlaveFormDto() {
    }

    public DeviceSlaveFormDto( Long masterId, Integer slaveId, String name, String deviceState, String deviceType, String deviceGroup,List<FunctionDto> functionDtos) {

        this.masterId = masterId;
        this.slaveId = slaveId;
        this.name = name;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.deviceGroup = deviceGroup;
        this.functionDtos=functionDtos;
    }

}
