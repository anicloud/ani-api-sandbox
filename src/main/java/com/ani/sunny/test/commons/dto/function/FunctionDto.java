package com.ani.sunny.test.commons.dto.function;

import com.ani.agent.service.commons.object.enumeration.FunctionType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanglina on 17-5-26.
 */
public class FunctionDto implements Serializable {
    public Integer stubId;
    public Long groupId;
    public String functionName;
    public FunctionType functionType;
    public List<FunctionArgumentDto> inputArgList;
    public List<FunctionArgumentDto> outputArgList;
    public Long featureId;

}
