package com.shrimp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.mapper.ContentMapper;
import com.shrimp.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-12<br>
 */
@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public EasyUIResult queryListByCategoryId(Long categoryId, Integer page, Integer rows) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, rows);
        List<Content> contents = contentMapper.queryListByCategoryId(categoryId);
        PageInfo<Content> pageInfo = new PageInfo<Content>(contents);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
