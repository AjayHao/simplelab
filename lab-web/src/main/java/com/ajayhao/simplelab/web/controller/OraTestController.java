package com.ajayhao.simplelab.web.controller;

import com.ajayhao.simplelab.web.service.OraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by AjayHao on 2017/6/22.
 */

@RestController
public class OraTestController {

    @Autowired
    private OraService oraService;

    @RequestMapping("/mail")
    @ResponseBody
    public String sendSms(@RequestParam String mobileNo, @RequestParam String content) {
        oraService.executeOraFunc();
        return "";
    }
}
