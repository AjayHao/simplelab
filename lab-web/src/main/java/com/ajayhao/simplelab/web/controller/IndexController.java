package com.ajayhao.simplelab.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajay.Hao on 2017/6/7.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(path="index", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request) throws Exception {
        return "/layout/content";
    }
    @RequestMapping(path="investinfo", method = RequestMethod.GET)
    public String investInfoPage(HttpServletRequest request) throws Exception {
        return "/invest/invest";
    }
}
