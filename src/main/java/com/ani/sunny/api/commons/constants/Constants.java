package com.ani.sunny.api.commons.constants;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.dto.app.AppInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-5-26.
 */
public class Constants {
    public static String ACCESS_TOKEN_SESSION_NAME;
    public static AppInfoDto APP_INFO_DTO;
    public static Map<Long,List<DeviceMasterObjInfoDto>> DEVICE_MASTER_MAPPINGS;
    public static Map<String,List<DeviceSlaveObjInfoDto>> SLAVE_OBJ_INFO_DTO_MAP;
    public static Map<String,List<StubInfoDto>> SLAVE_STUB_MAPPINGS;
}
