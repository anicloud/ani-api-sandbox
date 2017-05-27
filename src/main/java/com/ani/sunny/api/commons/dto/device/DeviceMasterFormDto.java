package com.ani.sunny.api.commons.dto.device;
import com.ani.sunny.api.commons.dto.function.FunctionDto;

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
    private List<FunctionDto> functionDtos;
    private List<DeviceSlaveFormDto> slaveDevices;

    public DeviceMasterFormDto() {
    }

    public DeviceMasterFormDto(Long masterId, Integer slaveId, String name,String description, String deviceState,List<FunctionDto>functionDtos, List<DeviceSlaveFormDto> slaveDevices) {

        this.masterId = masterId;
        this.slaveId = slaveId;
        this.name = name;
        this.description=description;
        this.deviceState = deviceState;
        this.functionDtos=(functionDtos==null)?new ArrayList<FunctionDto>():functionDtos;
        this.slaveDevices = (slaveDevices==null)?new ArrayList<DeviceSlaveFormDto>():slaveDevices;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Integer getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<FunctionDto> getFunctionDtos() {
        return functionDtos;
    }

    public void setFunctionDtos(List<FunctionDto> functionDtos) {
        this.functionDtos = functionDtos;
    }

    public List<DeviceSlaveFormDto> getSlaveDevices() {
        return slaveDevices;
    }

    public void setSlaveDevices(List<DeviceSlaveFormDto> slaveDevices) {
        this.slaveDevices = slaveDevices;
    }
}
