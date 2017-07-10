package com.ani.sunny.api.core.service.impl;

import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.core.service.facade.AppInfoInitService;
import com.ani.sunny.api.commons.dto.app.AppDto;
import com.ani.sunny.api.commons.dto.app.AppInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by lihui on 17-5-26.
 */
@Service
public class AppInfoInitServiceImpl implements AppInfoInitService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppInfoInitServiceImpl.class);
    private final static String FILE_PATH = "properties/AppInfo.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @PostConstruct
    public AppDto getAniServiceInfo() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(FILE_PATH);
        AppDto appDto = null;
        if (resource.exists()) {
            OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            appDto = OBJECT_MAPPER.readValue(resource.getURL(),AppDto.class);
        } else {
            LOGGER.error("service information file not exists.");
            throw new IOException("service information file not exists.");
        }
        Constants.APP_INFO_DTO = new AppInfoDto(appDto.aniServiceId, appDto.serviceName,appDto.clientSecret,appDto.webServerRedirectUri);

        return appDto;

    }
}
