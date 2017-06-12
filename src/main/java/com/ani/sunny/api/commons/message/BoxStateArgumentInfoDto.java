package com.ani.sunny.api.commons.message;

import com.ani.octopus.commons.state.dto.StateArgumentInfoDto;
import com.ani.utils.core.datatype.AniDataType;

/**
 * Created by zhanglina on 17-6-11.
 */
public class BoxStateArgumentInfoDto {
    public Object value;
    public String name;
    public  static BoxStateArgumentInfoDto fetchStateArgument(StateArgumentInfoDto stateArgumentInfoDto){
        BoxStateArgumentInfoDto boxStateArgumentInfoDto=new BoxStateArgumentInfoDto();
        boxStateArgumentInfoDto.value=stateArgumentInfoDto.value;
        boxStateArgumentInfoDto.name=stateArgumentInfoDto.name;
        return  boxStateArgumentInfoDto;
    }
}
