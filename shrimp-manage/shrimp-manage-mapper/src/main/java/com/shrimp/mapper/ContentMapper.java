package com.shrimp.mapper;

import com.github.abel533.mapper.Mapper;
import com.shrimp.pojo.Content;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-12<br>
 */
public interface ContentMapper extends Mapper<Content> {

    List<Content> queryListByCategoryId(Long categoryId);
}
