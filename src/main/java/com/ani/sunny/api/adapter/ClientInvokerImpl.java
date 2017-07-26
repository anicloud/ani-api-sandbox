package com.ani.sunny.api.adapter;

import com.ani.agent.service.core.validate.DomainObjectValidator;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.commons.message.WebSocketMessage;
import com.ani.sunny.api.commons.util.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.validation.ValidationException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhanglina on 17-6-2.
 */
public class ClientInvokerImpl implements ClientInvokable {
    @Override
    public List<StubArgumentDto> invokeAniObjectSync(AniStub stub) throws IOException, EncodeException {
//        if (!DomainObjectValidator.isDomainObjectValid(stub)) {
//            throw new ValidationException("Invalid AniStub Instance.");
//        }
//       List<StubArgumentDto> outputArguments=stub.outputArguments;
//        if(outputArguments==null){
//            outputArguments=new ArrayList<>();
//            StubArgumentDto stubArgumentDto=new StubArgumentDto();
//            stubArgumentDto.value="success";
//            outputArguments.add(stubArgumentDto);
//        }
//        return outputArguments;
        return null;
    }

    @Override
    public void sessionOnClose(String s, CloseReason closeReason) {
        System.out.println(closeReason+"----------------");
        // TODO
    }

    @Override
    public void sessionOnError(String s, Throwable throwable) {
        // TODO
    }

}
