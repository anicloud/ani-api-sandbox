package com.ani.sunny.api.commons.dto.app;

import java.io.Serializable;

/**
 * Created by wyf on 17-3-7.
 */
public class AppInfoDto implements Serializable{
    private static final long serialVersionUID = 6764360650981091678L;

    public String aniServiceId;
    public String serviceName;
    public String clientSecret;
    public String webServerRedirectUri;

    public AppInfoDto(){}

    public AppInfoDto(String aniServiceId, String serviceName, String clientSecret, String webServerRedirectUri){
        this.aniServiceId = aniServiceId;
        this.serviceName = serviceName;
        this.clientSecret = clientSecret;
        this.webServerRedirectUri = webServerRedirectUri;
    }
}
