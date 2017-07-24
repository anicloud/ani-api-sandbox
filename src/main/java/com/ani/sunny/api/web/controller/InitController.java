package com.ani.sunny.api.web.controller;

import com.ani.agent.service.core.config.AnicelMeta;
import com.ani.agent.service.core.websocket.WebSocketClient;
import com.ani.agent.service.core.websocket.WebSocketSessionFactory;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.aniservice.AniServiceAuth;
import com.ani.agent.service.service.aniservice.AniServiceInit;
import com.ani.agent.service.service.websocket.AccountNotify;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.agent.service.service.websocket.observer.AniObjectCallMessageObserver;
import com.ani.bus.service.commons.observer.MessageObserver;
import com.ani.bus.service.commons.session.AniServiceSession;
import com.ani.sunny.api.adapter.AccountNotifyImpl;
import com.ani.sunny.api.adapter.ClientInvokerImpl;
import com.ani.sunny.api.commons.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Vector;

/**
 * Created by wyf on 17-3-7.
 */
@Controller
public class InitController{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private AnicelMeta anicelMeta;
    @Resource
    private ObjectNotify objectNotify;
    @Resource
    private AniServiceInit aniServiceInit;
    @Resource
    private AniServiceAuth aniServiceAuth;
    @Autowired
    private AgentTemplate agentTemplate;
    @PostConstruct
    public void init() {
        try{
                Constants.ANI_SERVICE_SESSION= agentTemplate.connect(aniServiceAuth,aniServiceInit);
            LOGGER.info("build ani service session success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
