package com.ani.sunny.api.core.service.facade;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.sunny.api.commons.dto.user.UserDto;

/**
 * Created by lihui on 17-5-26.
 */
public interface ApplicationInitService {
    UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception;
}
