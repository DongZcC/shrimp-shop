package com.shrimp.web.pojo;

import org.springframework.util.StringUtils;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-16<br>
 */
public class Item extends com.shrimp.pojo.Item {
    public String[] getImages() {
        return StringUtils.split(super.getImage(), ",");
    }
}
