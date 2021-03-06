package com.ani.sunny.api.adapter;

import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.message.callmessage.AniStateObjectMessage;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.message.WebSocketMessage;
import com.ani.sunny.api.commons.util.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by wyf on 17-3-7.
 */
@Component("objectNotify")
public class ObjectNotifyImpl implements ObjectNotify{
    private static final Logger LOGGER = Logger.getLogger(ObjectNotifyImpl.class);

    @Override
    public void deviceConectedNotify(Long objectId, String description) {
        sendMassage(description);
    }

    @Override
    public void deviceDisconnectedNotify(Long objectId, String description) {
        //sendMassage(description);

    }

    private void updateObjectState(Long objectId, DeviceState state) throws Exception{

    }

    @Override
    public void deviceBoundNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, String description) {
    }

    @Override
    public void deviceUnBoundNotify(Long objectId, String description) {

    }

    @Override
    public void deviceSharedNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, Long hashUserId, String description) {
    }


    @Override
    public void deviceUnsharedNotify(Long objectId, Long hashUserId, String description) {
    }

    @Override
    public void deviceUpdatedNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto) {
       // List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtos=Constants.DEVICE_MASTER_MAPPINGS.get(deviceMasterObjInfoDto.owner.accountId);
       // Constants.SLAVE_OBJ_INFO_DTO_MAP.get(deviceMasterObjInfoDto.owner.accountId+":"+deviceMasterObjInfoDto.objectId);
//        List<StubInfoDto> stubs = deviceMasterObjInfoDto.stubs;
//        List<StubInfoDto> stubs1 = Constants.MASTER_STUB_MAPPING.get(deviceMasterObjInfoDto.owner.accountId+":"+deviceMasterObjInfoDto.objectId);
//        for (StubInfoDto stub:stubs){
//            boolean b=false;
//            for (StubInfoDto stub1:stubs1){
//                if (stub.group.groupId.equals(stub1.group.groupId)&&stub.stubId.equals(stub1.stubId)){
//
//                }
//            }
//        }
        Constants.MASTER_STUB_MAPPING.put(deviceMasterObjInfoDto.owner.accountId+":"+deviceMasterObjInfoDto.objectId,deviceMasterObjInfoDto.stubs);
        Constants.SLAVE_OBJ_INFO_DTO_MAP.put(deviceMasterObjInfoDto.owner.accountId+":"+deviceMasterObjInfoDto.objectId,deviceMasterObjInfoDto.slaves);
       // Constants.SLAVE_OBJ_INFO_DTO_MAP.put(deviceMasterObjInfoDto.owner.accountId+":"+deviceMasterObjInfoDto.objectId,deviceMasterObjInfoDto.slaves);
        //List<StubInfoDto> infoDtos= deviceMasterObjInfoDto.stubs;


    }

    @Override
    public void deviceStateUpdateNotify(AniStateObjectMessage stateMessage)  {

        Map<String, Object> data = new HashMap<>();
        data.put("Massage",stateMessage);
        try{
            WebSocketMessage webSocketMessage=new WebSocketMessage(Constants.userId,data,null);
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(webSocketMessage);
            Constants.messageList.clear();
            Constants.messageList.add(jsonData);


        }catch (IOException e){
            e.printStackTrace();

        }


    }
    private void sendMassage(String massage){
        Vector<WebSocketSession> sessions = SessionManager.getWebSocketSession(Constants.userId.toString());

        for (WebSocketSession session:sessions){
            try{

                TextMessage message = new TextMessage(massage);

                if (session.isOpen()){
                    session.sendMessage(message);
                }


            }catch (IOException e){
                e.printStackTrace();

            }

        }
    }
}
