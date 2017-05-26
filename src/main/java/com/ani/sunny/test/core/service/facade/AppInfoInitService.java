package com.ani.sunny.test.core.service.facade;

import com.ani.sunny.test.commons.dto.app.AppInfoDto;

import java.io.IOException;

/**
 * Created by lihui on 17-5-26.
 */
public interface AppInfoInitService {
    public AppInfoDto getAniServiceInfo() throws IOException;
}
