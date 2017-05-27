package com.ani.sunny.api.web.controller;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.sunny.api.commons.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 17-5-27.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private AgentTemplate agentTemplate;
    @RequestMapping("/master")
    @ResponseBody
    public Map<String,Object> getMasterDevices(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        Map<String,Object> result = new HashMap<String, Object>();
        if(StringUtils.isEmpty(accessToken))
            throw new RuntimeException("用户尚未登陆或token过期");
        AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
        try {
            List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtos = agentTemplate.getDeviceObjService(accessToken).getAccessibleDeviceObjInfoList(accountDto.accountId,Boolean.TRUE);
            result.put("result","success");
            result.put("description","查询成功");
            result.put("data",deviceMasterObjInfoDtos);
            Constants.DEVICE_MASTER_MAPPINGS.put(accountDto.accountId,deviceMasterObjInfoDtos);
        } catch (Exception e) {
            result.put("result","error");
            result.put("description","查询失败");
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping("/slave/{masterId}")
    public ModelAndView getSlaveDevices(HttpServletRequest request, @PathVariable Long masterId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        ModelAndView modelAndView = new ModelAndView("slave");

        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            boolean flag = false;
            if(Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId) != null)
            for(DeviceMasterObjInfoDto masterObjInfoDto:Constants.DEVICE_MASTER_MAPPINGS.get(accountDto.accountId)) {
                if(masterObjInfoDto.objectId.equals(masterId)) {
                    flag = true;
//                    result.put("result","success");
//                    result.put("data",masterObjInfoDto.slaves);
                    Constants.SLAVE_OBJ_INFO_DTO_MAP.put(accountDto.accountId+":"+masterId,masterObjInfoDto.slaves);

                    modelAndView.addObject("slaves",masterObjInfoDto.slaves);
                    modelAndView.addObject("masterId",masterObjInfoDto.objectId);

                    break;
                }
            }
            if(!flag) {
                  modelAndView.addObject("massage","主设备不存在");
//                result.put("result","error");
//                result.put("description","主设备不存在");
            }
        } else {
//            result.put("result","error");
//            result.put("description","主设备不存在");
            modelAndView.addObject("massage","主设备不存在");
        }
        return modelAndView;
    }
    @RequestMapping("/slave/stubs/{masterId}/{slaveId}")
    @ResponseBody
    public Map<String,Object> getSlaveStubs(HttpServletRequest request,@PathVariable Long masterId,@PathVariable Integer slaveId) {
        HttpSession session = request.getSession();
        String accessToken = String.valueOf(session.getAttribute(Constants.ACCESS_TOKEN_SESSION_NAME));
        Map<String,Object> result = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(accessToken)) {
            AccountDto accountDto = agentTemplate.getAccountService(accessToken).getByAccessToken();
            if(Constants.SLAVE_OBJ_INFO_DTO_MAP !=null && accountDto != null) {
                List<DeviceSlaveObjInfoDto> slaveObjInfoDtos = Constants.SLAVE_OBJ_INFO_DTO_MAP.get(accountDto.accountId + ":" + masterId);
                if (slaveObjInfoDtos != null) {
                    boolean flag = false;
                    for (DeviceSlaveObjInfoDto slaveObjInfoDto : slaveObjInfoDtos) {
                        if (slaveObjInfoDto.objectSlaveId.equals(slaveId)) {
                            flag = true;
                            result.put("result", "success");
                            result.put("data", slaveObjInfoDto.stubs);
                            Constants.SLAVE_STUB_MAPPINGS.put(masterId + ":" + slaveId, slaveObjInfoDto.stubs);
                        }
                    }
                    if (!flag) {
                        result.put("result", "error");
                        result.put("description", "从设备不存在");
                    }
                } else {
                    result.put("result", "error");
                    result.put("description", "对应主设备不存在");
                }
            } else {
                result.put("result","error");
                result.put("description","没找到从设备");
            }
        } else {
            result.put("result","error");
            result.put("description","用户未登陆或token过期");
        }
        return result;
    }
}
