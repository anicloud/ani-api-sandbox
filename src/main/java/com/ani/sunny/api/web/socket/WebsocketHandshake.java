package com.ani.sunny.api.web.socket;

import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by lihui on 2017/6/2.
 */
public class WebsocketHandshake extends HttpSessionHandshakeInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketHandshake.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        LOG.info("before hand shake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            Cookie[] cookies = serverHttpRequest.getServletRequest().getCookies();
            String hashUserId = null;
            for(int i=0;i<cookies.length;i++){
                if(Constants.SUNNY_HASH_USER_ID_COOKIE.equals(cookies[i].getName())){
                    hashUserId=Constants.userId.toString();
                   // hashUserId = cookies[i].getValue();
                }
            }
            HttpSession session = serverHttpRequest.getServletRequest().getSession();
            LOG.info("hashUserId:"+hashUserId+"session:"+session.getId());
            if (!"".equals(hashUserId)) {
                attributes.put("hashUserId", hashUserId);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        LOG.info("after hands shake");
        super.afterHandshake(request, response, wsHandler, ex);
    }


}
