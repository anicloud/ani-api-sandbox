package com.ani.sunny.api.web.socket;

import com.ani.agent.service.service.websocket.AccountInvoker;
import com.ani.agent.service.service.websocket.AniInvokerImpl;
import com.ani.bus.service.commons.dto.accountobject.AccountObject;
import com.ani.bus.service.commons.message.SocketMessage;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.util.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by lihui on 2017/6/2.
 */
public class WebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketHandler.class);

    public WebSocketHandler() {}

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String hashUserId = String.valueOf(session.getAttributes().get("hashUserId"));
        SessionManager.addSession(hashUserId, session);
        int sessionMapSize = SessionManager.getWebSocketSession(hashUserId).size();
        //向平台汇报账户状态：通知上线
        if(sessionMapSize <= 3) {
            AccountInvoker accountInvoker = new AniInvokerImpl(Constants.ANI_SERVICE_SESSION);
            AccountObject accountObj = new AccountObject(Long.parseLong(hashUserId),Constants.SUNNY_STUB_REGIST_MAP);
            SocketMessage socketMessage = accountInvoker.registerAndLogin(accountObj);
        }
        LOG.info("afterConnectionEstablished" + hashUserId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String hashUserId = session.getAttributes().get("hashUserId").toString();
        LOG.info("afterConnectionClosed" + hashUserId);
        SessionManager.removeSession(hashUserId, session.getId());
        int sessionMapSize = SessionManager.getWebSocketSession(hashUserId).size();
        //向平台汇报账户状态：通知下线
        if(sessionMapSize <=2) {
            AccountInvoker accountInvoker = new AniInvokerImpl(Constants.ANI_SERVICE_SESSION);
            AccountObject accountObj = new AccountObject(Long.parseLong(hashUserId));
            SocketMessage socketMessage = accountInvoker.logout(accountObj);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

}
