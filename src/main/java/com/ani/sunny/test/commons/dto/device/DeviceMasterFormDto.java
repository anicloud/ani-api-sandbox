package com.ani.sunny.test.commons.dto.device;
import com.ani.sunny.test.commons.dto.function.FunctionDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-5-26.
 */
public class DeviceMasterFormDto {
    private static final long serialVersionUID = -6294001276604571766L;

    private Long masterId;
    private Integer slaveId;
    private String name;
    private String description;
    private String deviceState;
    private String deviceType;
    private List<FunctionDto> functionDtos;
    private List<DeviceSlaveFormDto> slaveDevices;

    public DeviceMasterFormDto() {
    }

    public DeviceMasterFormDto(Long masterId, Integer slaveId, String name,String description, String deviceState, String deviceType, List<FunctionDto>functionDtos, List<DeviceSlaveFormDto> slaveDevices) {

        this.masterId = masterId;
        this.slaveId = slaveId;
        this.name = name;
        this.description=description;
        this.deviceState = deviceState;

        this.functionDtos=functionDtos;
        this.slaveDevices = (slaveDevices==null)?new ArrayList<DeviceSlaveFormDto>():slaveDevices;
    }

}
