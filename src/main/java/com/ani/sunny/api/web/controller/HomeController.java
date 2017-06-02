package com.ani.sunny.api.web.controller;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.sunny.api.commons.constants.Constants;
import com.ani.sunny.api.commons.dto.device.DeviceFormDto;
import com.ani.sunny.api.commons.util.OAuth2ParameterBuilder;
import com.ani.sunny.api.core.service.facade.ApplicationInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-5-26.
 */
@Controller
public class HomeController {
    @Autowired
    private AgentTemplate agentTemplate;
    @Autowired
    private ApplicationInitService initService;

    @RequestMapping(value = "/index")
    public String home() {
        return "index";
    }
    @RequestMapping(value ="/redirect")
    public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) {
        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.APP_INFO_DTO);
        AniOAuthAccessToken accessToken = agentTemplate.getAniOAuthService().getOAuth2AccessToken(code, authorizationCodeParameter);
        AccountDto accountDto = agentTemplate.getAccountService(accessToken.getAccessToken()).getByAccessToken();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SUNNY_HASH_USER_ID_SESSION,accountDto.accountId);
        session.setAttribute(Constants.ACCESS_TOKEN_SESSION_NAME,accessToken.getAccessToken());

        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName(Constants.SUNNY_HASH_USER_ID_COOKIE);
        cookieGenerator.setCookieMaxAge(-1);
        cookieGenerator.addCookie(response, String.valueOf(accountDto.accountId));

        ModelAndView modelAndView = new ModelAndView("device");
        List<DeviceFormDto> deviceFormDtos =new ArrayList<DeviceFormDto>();
        try {
            List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList = agentTemplate
                    .getDeviceObjService(accessToken.getAccessToken())
                    .getAccessibleDeviceObjInfoList(accountDto.accountId, Boolean.TRUE);
            if(deviceMasterObjInfoDtoList!=null && deviceMasterObjInfoDtoList.size()!=0){
                Constants.DEVICE_MASTER_MAPPINGS.put(accountDto.accountId,deviceMasterObjInfoDtoList);
                for (DeviceMasterObjInfoDto deviceMasterObjInfoDto:deviceMasterObjInfoDtoList){
                    DeviceFormDto deviceFormDto=DeviceFormDto.fetchDeviceMasterObjInfoDto(deviceMasterObjInfoDto);
                    deviceFormDtos.add(deviceFormDto);
                }

                modelAndView.addObject("masters",deviceFormDtos);
            }

        } catch (Exception e) {
            modelAndView.addObject("error","拉取设备失败");
        }
        return modelAndView;
    }
}
