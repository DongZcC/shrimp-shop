package com.shrimp.sso.service;

import com.shrimp.sso.mapper.UserMapper;
import com.shrimp.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-16<br>
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Boolean check(String param, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUserName(param);
                break;
            case 2:
                record.setPhone(param);
                break;
            case 3:
                record.setEmail(param);
                break;
            default:
                return null;
        }
        return this.userMapper.selectOne(record) == null;
    }
}
