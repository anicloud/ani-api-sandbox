package com.ani.sunny.api.commons.constants;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.octopus.commons.stub.dto.StubArgumentInfoDto;
import com.ani.bus.service.commons.session.AniServiceSession;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.ani.sunny.api.commons.dto.app.AppInfoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-5-26.
 */
public class Constants {
    public static String ACCESS_TOKEN_SESSION_NAME="tokenSessionName";
    public static AppInfoDto APP_INFO_DTO;
    public static AniServiceSession ANI_SERVICE_SESSION;
    public static Map<Long, List<Integer>> SUNNY_STUB_REGIST_MAP;
    public final static String SUNNY_HASH_USER_ID_COOKIE = "sunnyHashUserIdCookie";
    public static Long userId;
    public static final String SUNNY_HASH_USER_ID_SESSION = "sunnyHashUserIdSession";
    public static Map<Long,List<DeviceMasterObjInfoDto>> DEVICE_MASTER_MAPPINGS=new HashMap<>();
    public static Map<String,List<DeviceSlaveObjInfoDto>> SLAVE_OBJ_INFO_DTO_MAP=new HashMap<>();
    public static Map<String,List<StubInfoDto>> SLAVE_STUB_MAPPINGS=new HashMap<>();
}
