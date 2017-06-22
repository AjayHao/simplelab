package com.ajayhao.simplelab.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by AjayHao on 2017/6/22.
 */

@RestController
public class ParamTestController {
    @RequestMapping("/mail")
    @ResponseBody
    public String sendSms(@RequestParam String mobileNo, @RequestParam String content) {
        //中文处理
        String a = mobileNo;

        return "a:"+a+"b:"+content;
    }
}
