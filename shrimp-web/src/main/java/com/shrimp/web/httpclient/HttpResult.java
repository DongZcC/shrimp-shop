package com.shrimp.web.httpclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-12<br>
 */
@Getter
@Setter
@AllArgsConstructor
public class HttpResult {

    private int code;

    private String body;
}
