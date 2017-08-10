package com.ajayhao.simplelab.web.controller;

import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.service.CommonParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AjayHao on 2017/8/10.
 */
@RestController
@RequestMapping("/dict")
public class CommonParamController {
    private static final Logger logger = LoggerFactory.getLogger(CommonParamController.class);

    @Autowired
    private CommonParamService commonParamService;

    /*url参数*/
    @RequestMapping(method = {RequestMethod.GET})
    public List<CommonParamDTO> getCommonParamList(@RequestParam String groupName) {
        List<CommonParamDTO> commonParamDTOList = commonParamService.getParamsByGroup(groupName);
        return commonParamDTOList;
    }
}
