package com.ani.sunny.api.core.service.service.stub;

import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.constants.SunnyConstants;
import com.ani.sunny.api.commons.dto.stub.StubDto;
import com.ani.sunny.api.commons.dto.stub.StubInstanceDto;
import com.ani.sunny.api.commons.dto.stub.StubValueDto;
import com.ani.sunny.api.core.service.domian.Stub.StubInstance;
import org.springframework.stereotype.Service;

/**
 * Created by zhanglina on 17-5-31.
 */
@Service
public class StubInstanceServiceImpl implements StubInstanceService {

    @Override
    public Object invokeStubInstance(StubInstanceDto stubInstanceDto)throws Exception {
        StubInstance stubInstance= StubInstance.formDto(stubInstanceDto);
        stubInstance.execute();
        if (stubInstance.groupId==13 && stubInstance.stubId==1){
            synchronized (SunnyConstants.bytes) {
                SunnyConstants.stubValueDto.wait(10000000l);
            }
            return SunnyConstants.bytes;
        }
        if (stubInstance.groupId==512 && stubInstance.stubId==3){
            synchronized (SunnyConstants.stubValueDto) {
                SunnyConstants.stubValueDto.wait(10000000l);
            }

            return  SunnyConstants.stubValueDto.getValue();
        }

        return null;

    }
}
