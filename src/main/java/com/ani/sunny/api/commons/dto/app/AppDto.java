package com.ani.sunny.api.commons.dto.app;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.ani.bus.service.commons.dto.aniservice.AniServiceEntranceDto;
import com.ani.bus.service.commons.dto.aniservice.AniServiceInfoDto;
import com.ani.bus.service.commons.dto.aniservice.LanguageEnum;

import java.io.Serializable;
import java.util.*;

/**
 * Created by wyf on 17-3-7.
 */
public class AppDto implements Serializable {
    private static final long serialVersionUID = 2901849030510784877L;

    public Long id;
    public String aniServiceId;
    public String serviceName;
    public String version;
    public String clientSecret;
    public String resourceIds;
    public String scope;

    public String authorizedGrantTypes;
    public String authorities;

    public String webServerRedirectUri;
    public Integer accessTokenValidity;
    public Integer refreshTokenValidity;
    public String autoApprove;
    public Date registerDate;
    public boolean archived;
    public boolean trusted;             //to set weather the client is trusted, the field is just for grant_type = authorization_code, if false, go
    public String serviceServerUrl;
    public String logoPath;
    public String languageSet;

    public String tagSet;
    public Double price;

    public Date onShelf;
    public String description;
    public Long accountId;
    public List<AppEntranceDto> entranceList;

    public AppDto() {
    }

    public AppDto(Long id, String aniServiceId, String serviceName,
                  String version, String clientSecret,
                  String resourceIds, String scope,
                  String authorizedGrantTypes, String authorities,
                  String webServerRedirectUri, Integer accessTokenValidity,
                  Integer refreshTokenValidity, String autoApprove,
                  Date registerDate, boolean archived, boolean trusted,
                  String serviceServerUrl, String logoPath,
                  String languageSet, String tagSet, Double price, Date onShelf,
                  String description, Long accountId, List<AppEntranceDto> entranceList) {
        this.id = id;
        this.aniServiceId = aniServiceId;
        this.serviceName = serviceName;
        this.version = version;
        this.clientSecret = clientSecret;
        this.resourceIds = resourceIds;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.authorities = authorities;
        this.webServerRedirectUri = webServerRedirectUri;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.autoApprove = autoApprove;
        this.registerDate = registerDate;
        this.archived = archived;
        this.trusted = trusted;
        this.serviceServerUrl = serviceServerUrl;
        this.logoPath = logoPath;
        this.languageSet = languageSet;
        this.tagSet = tagSet;
        this.price = price;
        this.onShelf = onShelf;
        this.description = description;
        this.accountId = accountId;
        this.entranceList = entranceList;
    }
    public AniServiceDto toAniServiceDto(){
        Set<String> tagsets=new HashSet<String>();
        tagsets.add(this.tagSet);
        Set<LanguageEnum> languageSet=new HashSet<LanguageEnum>();
        languageSet.add(LanguageEnum.valueOf(this.languageSet));
        AniServiceInfoDto aniServiceInfo=new AniServiceInfoDto(
        this.serviceServerUrl ,
        this.logoPath,
        languageSet ,
               tagsets,
        this.price ,
        this.onShelf ,
        this.description

        );
        Set<String> resourceIds=new HashSet<String>();
                resourceIds.add(this.resourceIds);
        Set<String> scope=new HashSet<String>();
        scope.add(this.scope);
        Set<String> authorizedGrantTypes =new HashSet<String>();
        Collection<String> authorities =new ArrayList<String>();
        authorities.add(this.authorities);
        authorizedGrantTypes.add(this.authorizedGrantTypes);
        List<AniServiceEntranceDto> aniServiceEntranceDtos=new ArrayList<>(this.entranceList.size());
       for (AppEntranceDto appEntranceDto:this.entranceList){
           Set<String> tagSet=new HashSet<String>();
                   tagSet.add(appEntranceDto.tagSet);
           AniServiceEntranceDto aniServiceEntranceDto=new AniServiceEntranceDto(
                   appEntranceDto.id,
                   appEntranceDto.entranceName,
                   appEntranceDto.entranceUrl,
                   appEntranceDto.logoPath,
                   tagSet,
                   appEntranceDto.description
           );
           aniServiceEntranceDtos.add(aniServiceEntranceDto);
       }
        AniServiceDto aniServiceDto=new AniServiceDto(
                this.aniServiceId,
                this.serviceName,
                this.version,
                this.clientSecret,
                resourceIds,
                scope ,
                authorizedGrantTypes,
                authorities ,
                this.webServerRedirectUri ,
                this.accessTokenValidity,
                this.refreshTokenValidity ,
                this.autoApprove ,
                this.registerDate ,
                this.archived ,
                this.trusted ,
                this.accountId ,
                aniServiceEntranceDtos,
                aniServiceInfo


        );

      return aniServiceDto;

    }
    @Override
    public String toString() {
        return "AniServiceDao{" +
                "id=" + id +
                ", aniServiceId='" + aniServiceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
