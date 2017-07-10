package com.ani.sunny.api.adapter;

import com.ani.agent.service.service.aniservice.AniServiceInit;
import com.ani.agent.service.service.websocket.AccountNotify;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.agent.service.service.websocket.ObjectNotify;
import org.springframework.stereotype.Component;

/**
 * Created by zhanglina on 17-6-26.
 */
@Component
public class AniServiceInitImpl implements AniServiceInit {
    @Override
    public ObjectNotify getObjectNotify() {
        return new ObjectNotifyImpl();
    }

    @Override
    public ClientInvokable getInvokable() {
        return new ClientInvokerImpl();
    }

    @Override
    public AccountNotify getAccountNotify() {
       return  new AccountNotifyImpl();
    }
}
