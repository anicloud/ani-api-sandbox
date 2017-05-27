package com.ani.sunny.test.web.controller;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.sunny.test.commons.constants.Constants;
import com.ani.sunny.test.commons.dto.user.UserDto;
import com.ani.sunny.test.commons.util.OAuth2ParameterBuilder;
import com.ani.sunny.test.core.service.facade.ApplicationInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lihui on 17-5-26.
 */
@Controller
public class HomeController {
    @Autowired
    private AgentTemplate agentTemplate;
    @Autowired
    private ApplicationInitService initService;
    @RequestMapping(value ="/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) {
        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.appInfoDto);
        AniOAuthAccessToken oAuth2AccessToken = agentTemplate.getAniOAuthService().getOAuth2AccessToken(code, authorizationCodeParameter);
        try {
            UserDto userDto = initService.initApplication(oAuth2AccessToken);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
