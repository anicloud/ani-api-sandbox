package com.ani.sunny.api.commons.dto.stub;

import com.ani.octopus.commons.stub.dto.StubInfoDto;

import java.util.List;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubDto {
    private Integer stubId;
    private String name;
    private Long groupId;
    private List<StubArgumentDto> inputArguments;
    private List<StubArgumentDto> outputArguments;
    private Long masterId;
    private Integer slaveId;

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

    public Integer getStubId() {
        return stubId;
    }

    public void setStubId(Integer stubId) {
        this.stubId = stubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<StubArgumentDto> getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(List<StubArgumentDto> inputArguments) {
        this.inputArguments = inputArguments;
    }

    public List<StubArgumentDto> getOutputArguments() {
        return outputArguments;
    }

    public void setOutputArguments(List<StubArgumentDto> outputArguments) {
        this.outputArguments = outputArguments;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    private String privilegeType;

    public StubDto(){}
    public StubDto(Integer stubId,String name,Long masterId,Integer slaveId ){
        this.stubId=stubId;
        this.name=name;
        this.masterId=masterId;
        this.slaveId=slaveId;
    }
    public static StubDto fetchStubInfoDto(StubInfoDto stubInfoDto){
        StubDto stubDto=new StubDto();
        stubDto.stubId=stubInfoDto.stubId;
        stubDto.name=stubInfoDto.name;
        stubDto.groupId=stubInfoDto.group.groupId;
        stubDto.inputArguments=StubArgumentDto.fetchStubArgumentInfoDtos(stubInfoDto.inputArguments);
        stubDto.privilegeType=stubInfoDto.privilegeType.toString();
        return stubDto;
    }
}
