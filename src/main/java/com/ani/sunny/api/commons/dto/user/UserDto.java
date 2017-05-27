package com.ani.sunny.api.commons.dto.user;

import com.ani.sunny.api.commons.dto.device.DeviceMasterFormDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -3722526475602732820L;

    public Long hashUserId;
    public String email;
    public String screenName;

    public String accessToken;
    public String tokenType;
    public String refreshToken;
    public Long expiresIn;
    public String scope;
    public Long createTime;
    public List<DeviceMasterFormDto> masterList;
    public UserDto() {
    }

    public UserDto(String accessToken, String email,
                   Long expiresIn, Long hashUserId,
                   String refreshToken, String scope,
                   String screenName, String tokenType,
                   Long createTime) {
        this.accessToken = accessToken;
        this.email = email;
        this.expiresIn = expiresIn;
        this.hashUserId = hashUserId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.screenName = screenName;
        this.tokenType = tokenType;
        this.createTime = createTime;
    }

    public UserDto(String accessToken, Long createTime, String email,
                   Long expiresIn, Long hashUserId,
                   String refreshToken, String scope,
                   String screenName, String tokenType,List<DeviceMasterFormDto> masterList) {
        this.accessToken = accessToken;
        this.createTime = createTime;
        this.email = email;
        this.expiresIn = expiresIn;
        this.hashUserId = hashUserId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.screenName = screenName;
        this.tokenType = tokenType;
        this.masterList=masterList;

    }

    @Override
    public String toString() {
        return "UserDto{" +
                "accessToken='" + accessToken + '\'' +
                ", hashUserId='" + hashUserId + '\'' +
                ", email='" + email + '\'' +
                ", screenName='" + screenName + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
