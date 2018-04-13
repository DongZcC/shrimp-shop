package com.shrimp.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-13<br>
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
}
