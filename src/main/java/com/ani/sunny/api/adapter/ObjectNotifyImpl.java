package com.ani.sunny.api.adapter;

import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.message.callmessage.AniStateObjectMessage;
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
        try {
            updateObjectState(objectId, DeviceState.CONNECTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deviceDisconnectedNotify(Long objectId, String description) {
        try {
            updateObjectState(objectId, DeviceState.DISCONNECTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void deviceStateUpdateNotify(AniStateObjectMessage aniStateObjectMessage)  {
        Vector<WebSocketSession> sessions = SessionManager.getWebSocketSession(Constants.userId.toString());
        Map<String, Object> data = new HashMap<>();
        data.put("massage",aniStateObjectMessage);
        for (WebSocketSession session:sessions){
            try{
                WebSocketMessage webSocketMessage=new WebSocketMessage(Constants.userId,data,null);
                ObjectMapper mapper = new ObjectMapper();
                String jsonData = mapper.writeValueAsString(webSocketMessage);
                TextMessage message = new TextMessage(jsonData);
                session.sendMessage(message);

            }catch (IOException e){
                System.out.println(e);

            }

        }


    }

    public static void main(String[] args) {

    }
}