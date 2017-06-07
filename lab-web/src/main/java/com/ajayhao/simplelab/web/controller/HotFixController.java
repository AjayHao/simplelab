package com.ajayhao.simplelab.web.controller;

import com.ajayhao.core.enums.BizCode;
import com.ajayhao.core.util.CoreObjectUtils;
import com.ajayhao.simplelab.base.BaseResponse;
import com.ajayhao.simplelab.web.toolbox.AllRounderTool;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by ajay.hao on 2016/11/17.
 */
@Controller
@RequestMapping(value = "/emergency", produces = "text/html;charset=UTF-8")
public class HotFixController {
    private static final Logger logger = LoggerFactory.getLogger(HotFixController.class);

    @Autowired
    private AllRounderTool allRounderTool;

    @RequestMapping("repair.htm")
    @ResponseBody
    public String prepare(@RequestParam("beanId")String beanId
            , @RequestParam("methodName")String methodName
            , @RequestParam("paramTypeStr")String paramTypeStr
            , @RequestParam("paramJsonStr")String paramJsonStr) {

        Map<String,Object> resultMap = new HashedMap();

        BaseResponse response = new BaseResponse();

        if(StringUtils.isAnyEmpty(beanId,methodName)){
            response.setCode(BizCode.ParamError);
            response.setMessage("执行失败:传入的beanId或方法名为空");
        }

        if(StringUtils.isBlank(paramTypeStr)){
            paramTypeStr = "";
            paramJsonStr = "";
        }else{
            paramTypeStr = paramTypeStr.replace(" ","").replace(",", "\",\"");
            paramTypeStr = "[\"" + paramTypeStr + "\"]";
            paramJsonStr = "[" + paramJsonStr + "]";
        }

        try {
            Object result = allRounderTool.execute(beanId, methodName, paramTypeStr, paramJsonStr);
            response.setCode(BizCode.Success);
            response.setMessage("执行成功!");
            resultMap.put("返回值",result);
        }catch(Exception e){
            response.setCode(BizCode.Unknown);
            response.setMessage("执行失败：e=" + ExceptionUtils.getStackTrace(e));
        }

        resultMap.put("请求返回",response);
        return CoreObjectUtils.object2Json(resultMap);
    }
}
