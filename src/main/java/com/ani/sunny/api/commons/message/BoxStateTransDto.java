package com.ani.sunny.api.commons.message;

import com.ani.octopus.commons.state.dto.StateArgumentInfoDto;
import com.ani.octopus.commons.state.dto.StateTransDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 17-6-11.
 */
public class BoxStateTransDto {
    public Long stateGroupid;
    public Integer stateId;
    public String description;
    public List<BoxStateArgumentInfoDto> stateArgumentInfoDtoList;
    public static BoxStateTransDto fetchStateTransDto(StateTransDto stateTransDto){
        BoxStateTransDto boxStateTransDto=new BoxStateTransDto();
        boxStateTransDto.stateId=stateTransDto.stateId;
        boxStateTransDto.stateGroupid=stateTransDto.stateGroupid;
        boxStateTransDto.description=stateTransDto.description;
        List<BoxStateArgumentInfoDto> boxStateArgumentInfoDtos=new ArrayList<>();
        for(StateArgumentInfoDto stateArgumentInfoDto:stateTransDto.stateArgumentInfoDtoList){
            boxStateArgumentInfoDtos.add( BoxStateArgumentInfoDto.fetchStateArgument(stateArgumentInfoDto));

        }
        boxStateTransDto.stateArgumentInfoDtoList=boxStateArgumentInfoDtos;
        return boxStateTransDto;
    }
}
