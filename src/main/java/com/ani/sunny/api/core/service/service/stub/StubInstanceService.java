package com.ani.sunny.api.core.service.service.stub;

import com.ani.sunny.api.commons.dto.stub.StubDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.core.service.domian.Stub.StubInstance;

/**
 * Created by zhanglina on 17-5-31.
 */
public interface StubInstanceService {
    boolean invokeStubInstance(StubInstanceDto stubInstanceDto)throws Exception;
}
