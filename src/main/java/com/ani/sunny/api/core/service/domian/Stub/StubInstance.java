package com.ani.sunny.api.core.service.domian.Stub;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.websocket.AniInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.message.http.Message;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;

import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.core.service.domian.Device.Device;
import com.ani.sunny.api.core.service.utils.SpringContextHolder;
import com.ani.utils.core.datatype.AniDataType;
import com.ani.utils.core.datatype.AniDataTypeCategories;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhanglina on 17-5-31.
 */
public class StubInstance {
    public Integer stubId;
    public String name;
    public Long groupId;
    public Long masterId;
    public Long userId;
    public Integer deviceId;
    public List<StubArgInstance> inputList;
    public boolean execute(){


        try {
            AniStub aniStub = new AniStub(
                    masterId,
                    deviceId,
                    userId,
                    groupId,
                    stubId,
                    convert(inputList)
            );
            AgentTemplate agentTemplate = (AgentTemplate) SpringContextHolder.getBean("agentTemplate");
            AniInvokable aniInvokable = agentTemplate.getAniInvokable(Constants.ANI_SERVICE_SESSION);
           // LOGGER.info("----Stub invoke :"+groupId+" "+stubId +", SystemTime is : " + System.currentTimeMillis());
            aniInvokable.invokeAniObjectSync(aniStub);
          //  LOGGER.info("----Stub invoke result receive, " +"SystemTime is : " + System.currentTimeMillis());

        } catch (IOException | EncodeException e) {
            //LOGGER.error("function execute failed, stubId is {}, groupId is {},result {}.",
                //    stubId, groupId, e.getMessage());
            e.printStackTrace();
        }
        return true;


    }
    public static List<StubArgumentDto> convert(List<StubArgInstance> inputList) {
        if (inputList != null && inputList.size() > 0) {
            List<StubArgumentDto> argumentList;
            argumentList =new ArrayList<StubArgumentDto>();
            for (StubArgInstance argument : inputList) {
                StubArgumentDto aniArgument = new StubArgumentDto(convetValueToCorrectObject(argument.value,argument.aniDataType));
                argumentList.add(aniArgument);
            }
            return argumentList;
        } else {
            return null;
        }
    }
    public static Object convetValueToCorrectObject(Object value, AniDataType dataType) {
        if(value == null){
            value = 0;
        }
        if (dataType.getCategory() == AniDataTypeCategories.PRIMITIVE) {
            switch (dataType.getDataType()){
                case INTEGER:
                    return new Integer(value.toString());
                case LONG:
                    return new Long(value.toString());
                case FLOAT:
                    return new Float(value.toString()+'f');
                case BOOLEAN:
                    return Boolean.parseBoolean(value.toString());
                case SHORT:
                    return new Short(value.toString());
                case BINARY:
                    return value.toString().getBytes();
                case CHAR:
                    return value.toString().charAt(0);
                case PERCENTAGE:
                    return new Short(value.toString());
                case STRING:
                    return value.toString();
                case OBJECT:
                    return value;
            }
        } else {
            //todo
        }
        return value;
    }
    public static StubInstance formDto(StubInstanceDto stubInstanceDto){
        StubInstance stubInstance=new StubInstance();
        if(stubInstanceDto!=null){
            stubInstance.masterId=stubInstanceDto.masterId;
            if (stubInstanceDto.deviceId.intValue()==-1){
                stubInstance.deviceId=null;
            }else {
                stubInstance.deviceId=stubInstanceDto.deviceId.intValue();
            }


            stubInstance.stubId=stubInstanceDto.stubId;
            stubInstance.name=stubInstanceDto.name;
            stubInstance.groupId=stubInstanceDto.groupId;
            stubInstance.userId=stubInstanceDto.userId;
            stubInstance.inputList=StubArgInstance.fromDtoList(stubInstanceDto.inputList);

        }
        return  stubInstance;
    }

}
