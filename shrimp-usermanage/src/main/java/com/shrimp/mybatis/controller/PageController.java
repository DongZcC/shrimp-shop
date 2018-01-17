package com.shrimp.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-17<br>
 */
@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping(value = "{pageName}")
    public String toPage(@PathVariable("pageName") String pageName) {
        return pageName;
    }
}
