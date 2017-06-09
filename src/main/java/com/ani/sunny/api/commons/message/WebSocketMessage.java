package com.ani.sunny.api.commons.message;

import java.io.Serializable;

/**
 * Created by wyf on 17-3-21.
 */
public class WebSocketMessage implements Serializable{
    public Long hashUserId;
    public Object messageInstance;
    public MessageType messageType;

    public WebSocketMessage(){}

    public WebSocketMessage(Long hashUserId, Object messageInstance, MessageType messageType){
        this.hashUserId = hashUserId;
        this.messageType = messageType;
        this.messageInstance = messageInstance;
    }
}
