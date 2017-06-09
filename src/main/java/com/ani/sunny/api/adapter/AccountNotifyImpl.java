package com.ani.sunny.api.adapter;

import com.ani.agent.service.service.websocket.AccountInvoker;
import com.ani.agent.service.service.websocket.AccountNotify;
import com.ani.agent.service.service.websocket.AniInvokerImpl;
import com.ani.bus.service.commons.dto.accountobject.AccountObject;
import com.ani.bus.service.commons.message.SocketMessage;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.util.SessionManager;
import javax.websocket.EncodeException;
import java.io.IOException;

/**
 * Created by wyf on 17-3-21.
 */
public class AccountNotifyImpl implements AccountNotify{
    @Override
    public void accountReconnectNotify() {
        AccountInvoker accountInvoker = new AniInvokerImpl(Constants.ANI_SERVICE_SESSION);
        try {
            for (String hashUserId: SessionManager.getAllAccounts()) {
                AccountObject accountObj = new AccountObject(Long.valueOf(hashUserId),Constants.SUNNY_STUB_REGIST_MAP);
                SocketMessage socketMessage = accountInvoker.registerAndLogin(accountObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
