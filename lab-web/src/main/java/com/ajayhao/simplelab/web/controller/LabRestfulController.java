package com.ajayhao.simplelab.web.controller;

import com.ajayhao.simplelab.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AjayHao on 2017/6/27.
 */
@RestController
public class LabRestfulController {

    @Autowired
    private LabService labService;

    @RequestMapping("/pushMsg")
    @ResponseBody
    public String sendSms(@RequestParam String topic, @RequestParam String msg) {
        labService.pushMsg(topic,msg);
        return "";
    }
}
