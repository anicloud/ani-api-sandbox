package com.ani.sunny.api.core.service.impl;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.sunny.api.commons.dto.device.DeviceMasterFormDto;
import com.ani.sunny.api.commons.dto.device.DeviceSlaveFormDto;
import com.ani.sunny.api.commons.dto.function.FunctionDto;
import com.ani.sunny.api.commons.dto.user.UserDto;
import com.ani.sunny.api.core.service.facade.ApplicationInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihui on 17-5-26.
 */
@Service
public class ApplicationInitServiceImpl implements ApplicationInitService {
    @Autowired
    private AgentTemplate agentTemplate;
    public UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception {
        AccountDto accountDto = agentTemplate
                .getAccountService(accessToken.getAccessToken())
                .getByAccessToken();
        UserDto userDto = fetchUserInfo(accountDto, accessToken);
        //获取msater列表
        List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList = agentTemplate
                .getDeviceObjService(accessToken.getAccessToken())
                .getAccessibleDeviceObjInfoList(userDto.hashUserId, Boolean.TRUE);
        //获取slave列表
        for (DeviceMasterObjInfoDto deviceMasterObjInfoDto:deviceMasterObjInfoDtoList){
                fetchDeviceMasterObjInfo(deviceMasterObjInfoDto);
        }

        return userDto;
    }
    private UserDto fetchUserInfo(AccountDto accountDto, AniOAuthAccessToken accessToken) throws Exception {
        return new UserDto(
                accessToken.getAccessToken(),
                accountDto.email,
                accessToken.getExpiresIn(),
                accountDto.accountId,
                accessToken.getRefreshToken(),
                accessToken.getScope(),
                accountDto.screenName,
                accessToken.getTokenType(),
                System.currentTimeMillis()
        );
    }
    private DeviceMasterFormDto fetchDeviceMasterObjInfo(DeviceMasterObjInfoDto deviceMasterObjInfoDto){

//        return new DeviceMasterFormDto(
//                -1,
//                deviceMasterObjInfoDto.objectId.intValue(),
//                deviceMasterObjInfoDto.name,
//                deviceMasterObjInfoDto.description,
//                deviceMasterObjInfoDto.state,
//
//
//
//
//
//        );
        return null;

    }
    private FunctionDto fetchFunctionFromStub(){
        return null;
    }
    private DeviceSlaveFormDto fetchDeviceSlaveObjInfo(DeviceSlaveObjInfoDto deviceSlaveObjInfoDto){
        return null;
    }
}
