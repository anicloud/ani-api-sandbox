package com.ani.sunny.api.web.controller;

import com.ani.agent.service.core.config.AnicelMeta;
import com.ani.agent.service.core.websocket.WebSocketClient;
import com.ani.agent.service.core.websocket.WebSocketSessionFactory;
import com.ani.agent.service.service.websocket.AccountNotify;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.agent.service.service.websocket.observer.AniObjectCallMessageObserver;
import com.ani.bus.service.commons.observer.MessageObserver;
import com.ani.sunny.api.adapter.AccountNotifyImpl;
import com.ani.sunny.api.adapter.ClientInvokerImpl;
import com.ani.sunny.api.commons.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostConstruct
    public void init() {
        try{
            // you need to implement the Invokable interface and register on
            // WebSocketClient for anicloud platform to callback
            ClientInvokable invokable = new ClientInvokerImpl();
            AccountNotify accountNotify = new AccountNotifyImpl();
            WebSocketClient socketClient = new WebSocketClient(invokable,objectNotify,accountNotify);
//            WebSocketClient socketClient = new WebSocketClient(invokable,objectNotify);

            // you need to implement your own observer and register on socketClient
            // to receive the message from anicloud platform
            Vector<MessageObserver> messageObservers = new Vector<>();
            messageObservers.add(new AniObjectCallMessageObserver());
            socketClient.setObs(messageObservers);

            // inject your WebSocketClient instance and anicloud socket destination url to factory
            // and use factory to get the session, than you can use the session to communicate
            // with anicloud platform
            WebSocketSessionFactory sessionFactory = new WebSocketSessionFactory(
                    socketClient,
                    anicelMeta,
                    Constants.APP_INFO_DTO.aniServiceId,
                    Constants.APP_INFO_DTO.clientSecret
            );
            Constants.ANI_SERVICE_SESSION = sessionFactory.getAniServiceSession();
            LOGGER.info("build ani service session success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
