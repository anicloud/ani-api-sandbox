package com.ani.sunny.api.core.service.domian.Stub.init;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.sunny.api.commons.stub.SunnyStub;

import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 16-10-28.
 */
public interface SunnyStubPersistService {
    Map<Integer,SunnyStub> fetchSunnyStubMappings();
    Map<Long, List<Integer>> getSunnyStubMap();
    void update(AniStub aniStub, SunnyStub sunnyStub) throws Exception;
}
