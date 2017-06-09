package com.ani.sunny.api.core.service.service.stub;

import com.ani.sunny.api.commons.dto.stub.StubDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.core.service.domian.Stub.StubInstance;
import org.springframework.stereotype.Service;

/**
 * Created by zhanglina on 17-5-31.
 */
@Service
public class StubInstanceServiceImpl implements StubInstanceService {

    @Override
    public boolean invokeStubInstance(StubInstanceDto stubInstanceDto)throws Exception {
        StubInstance stubInstance= StubInstance.formDto(stubInstanceDto);

        return  stubInstance.execute();

    }
}
