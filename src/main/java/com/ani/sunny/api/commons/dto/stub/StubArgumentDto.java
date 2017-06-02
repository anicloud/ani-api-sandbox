package com.ani.sunny.api.commons.dto.stub;

import com.ani.octopus.commons.stub.dto.StubArgumentInfoDto;
import com.ani.utils.core.datatype.AniDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-5-31.
 */
public class StubArgumentDto {
    private AniDataType dataType;
    private String name;

    public AniDataType getDataType() {
        return dataType;
    }

    public void setDataType(AniDataType dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  StubArgumentDto(){}
    public  StubArgumentDto(String name){
        this.name=name;
    }
    public static List<StubArgumentDto> fetchStubArgumentInfoDtos(List<StubArgumentInfoDto> stubArgumentInfoDtos){
        List<StubArgumentDto> stubArgumentDtos=new ArrayList<StubArgumentDto>();
        for (StubArgumentInfoDto stubArgumentInfoDto:stubArgumentInfoDtos){
            StubArgumentDto stubArgumentDto=new StubArgumentDto();
            stubArgumentDto.name=stubArgumentInfoDto.name;
            stubArgumentDto.dataType=stubArgumentInfoDto.dataType;
            stubArgumentDtos.add(stubArgumentDto);
        }
        return stubArgumentDtos;
    }
}
