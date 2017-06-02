package com.ani.sunny.api.commons.constants;

import com.ani.bus.service.commons.session.AniServiceSession;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglina on 17-5-31.
 */
public class SunnyConstants {
    public static Map<String, String> SUNNY_NAME_MAPPINGS;
   // public static Map<Integer, SunnyStub> SUNNY_STUB_MAPPINGS;

    public static Map<String,Object> STATE_STUB_MAPPINGS;

    private SunnyConstants() {}

    public final static String SUNNY_COOKIE_USER_NAME = "sunny_user";
    public final static String SUNNY_SESSION_NAME = "sunny_session";
    public final static String SUNNY_DEVICE_DEFAULT_GROUP = "default";
    public final static String SUNNY_DEVICE_INIT_PARAM = "{}";

    /**
     * the pattern type of the type trigger
     */
    public final static String TIME_TRIGGER_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static Long TOKEN_REFRESH_TIME_INTERVAL_IN_SECONDS = 60 * 60L;

    //public static AppInfoDto appInfoDto = null;
    /**
     * the web socket session
     */
    public static AniServiceSession aniServiceSession;
    /**
     * service Object向平台注册stub用
     */
    public static Map<Long, List<Integer>> SUNNY_STUB_REGIST_MAP;
}
