package com.ani.sunny.api.adapter;

import com.ani.agent.service.core.validate.DomainObjectValidator;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;

import javax.validation.ValidationException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhanglina on 17-6-2.
 */
public class ClientInvokerImpl implements ClientInvokable {
    @Override
    public List<StubArgumentDto> invokeAniObjectSync(AniStub stub) throws IOException, EncodeException {
        if (!DomainObjectValidator.isDomainObjectValid(stub)) {
            throw new ValidationException("Invalid AniStub Instance.");
        }
        StubInstanceDto stubInstanceDto=StubInstanceDto.fetchAniStub(stub);

        return null;
    }

    @Override
    public void sessionOnClose(String s, CloseReason closeReason) {
        // TODO
    }

    @Override
    public void sessionOnError(String s, Throwable throwable) {
        // TODO
    }

}
