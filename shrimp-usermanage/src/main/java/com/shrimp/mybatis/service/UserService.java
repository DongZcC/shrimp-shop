package com.shrimp.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shrimp.mybatis.bean.DataGridResult;
import com.shrimp.mybatis.mapper.UserMapper;
import com.shrimp.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-17<br>
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public DataGridResult queryUserList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> users = userMapper.select(null);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
