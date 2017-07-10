package com.ani.sunny.api.adapter;

import com.ani.agent.service.service.aniservice.AniServiceAuth;
import com.ani.bus.service.commons.dto.aniservice.*;
import com.ani.sunny.api.commons.dto.app.AppDto;
import com.ani.sunny.api.commons.dto.app.AppEntranceDto;
import com.ani.sunny.api.core.service.facade.AppInfoInitService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhanglina on 17-6-26.
 */
@Component
public class AniServiceAuthImpl implements AniServiceAuth{
    @Resource
    private AppInfoInitService appInfoInitService;

    @Override
    public AniServiceDto getAniService() {
        try {
          AppDto appDto= appInfoInitService.getAniServiceInfo();
           return appDto.toAniServiceDto();

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public AniServiceRegisterDto getAniServiceRegisterDto() {
        try {
            AppDto appDto=appInfoInitService.getAniServiceInfo();
            Set<String> tagsets=new HashSet<String>();
            tagsets.add(appDto.tagSet);
            Set<LanguageEnum> languageSet=new HashSet<LanguageEnum>();
            languageSet.add(LanguageEnum.valueOf(appDto.languageSet));
            AniServiceInfoDto aniServiceInfo=new AniServiceInfoDto(
                    appDto.serviceServerUrl ,
                    appDto.logoPath,
                    languageSet ,
                    tagsets,
                    appDto.price ,
                    appDto.onShelf ,
                    appDto.description

            );
            List<AniServiceEntranceDto> aniServiceEntranceDtos=new ArrayList<>();
            for (AppEntranceDto appEntranceDto:appDto.entranceList){
                Set<String> tagSet=new HashSet<String>();
                tagSet.add(appEntranceDto.tagSet);
                AniServiceEntranceDto aniServiceEntranceDto=new AniServiceEntranceDto(
                        appEntranceDto.entranceName,
                        appEntranceDto.entranceUrl,
                        appEntranceDto.logoPath,
                        tagSet,
                        appEntranceDto.description
                );
                aniServiceEntranceDtos.add(aniServiceEntranceDto);
            }
            AniServiceRegisterDto aniServiceRegisterDto=new AniServiceRegisterDto(
                    appDto.serviceName,
                    appDto.version,
                    appDto.webServerRedirectUri,
                    appDto.accountId,
                    aniServiceEntranceDtos,
                    aniServiceInfo,
                    new HashMap<>()
            );
            return aniServiceRegisterDto;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void onRegistenFinished(AniServiceDto aniServiceDto) {

    }
}
