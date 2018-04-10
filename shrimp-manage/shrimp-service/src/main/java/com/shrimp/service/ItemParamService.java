package com.shrimp.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.pojo.Item;
import com.shrimp.pojo.ItemParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-10<br>
 */
@Service
public class ItemParamService extends BaseService<ItemParam> {

    public EasyUIResult queryItemList(int page, int rows) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, rows);
        Example example = new Example(ItemParam.class);
        example.setOrderByClause("updated DESC");
        List<ItemParam> itemParams = mapper.selectByExample(example);
        PageInfo<ItemParam> pageInfo = new PageInfo<>(itemParams);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
