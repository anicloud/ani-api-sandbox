package com.ani.sunny.api.commons.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihui on 17-6-2.
 */
public class SessionManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    /**
     * key - hashUserId:sessionId
     */
    private static Map<String, Vector<WebSocketSession>> userSessionMaps = new ConcurrentHashMap<String, Vector<WebSocketSession>>();

    public static void removeSession(String hashUserId, String sessionId) throws IOException, EncodeException {
        if (StringUtils.isNotEmpty(hashUserId)) {
            Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
            if (sessionVector != null) {
                int index = -1;

                for (int i=0;  i<sessionVector.size(); i++) {
                    WebSocketSession socketSession = sessionVector.get(i);
                    if (socketSession.getId().equals(sessionId)) {
                        index = i;
                        break;
                    }
                }
                sessionVector.removeElementAt(index);
            }
        }
    }

    public static void addSession(String hashUserId, WebSocketSession session) throws IOException, EncodeException {
        Vector<WebSocketSession> sessionVector = null;
        sessionVector = userSessionMaps.get(hashUserId);

        if (sessionVector != null) {
            sessionVector.add(session);
        } else {
            sessionVector = new Vector<WebSocketSession>();
            sessionVector.add(session);
        }
        userSessionMaps.put(hashUserId, sessionVector);
    }

    public static WebSocketSession getWebSocketSession(String hashUserId, String sessionId) {
        Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
        if (sessionVector != null) {
            for (WebSocketSession socketSession : sessionVector) {
                if (socketSession.getId().equals(sessionId)) {
                    return socketSession;
                }
            }
        }
        return null;
    }

    public static Vector<WebSocketSession> getWebSocketSession(String hashUserId) {
        Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
        return sessionVector;
    }

    public static Set<String> getAllAccounts() {
        return userSessionMaps.keySet();
    }

}
