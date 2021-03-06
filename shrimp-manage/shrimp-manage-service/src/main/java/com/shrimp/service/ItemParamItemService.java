package com.shrimp.service;

import com.github.abel533.entity.Example;
import com.shrimp.mapper.ItemParamItemMapper;
import com.shrimp.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-10<br>
 */
@Service
public class ItemParamItemService extends BaseService<ItemParamItem> {

    public int updateItemParamItem(Long itemId, String itemParams) {
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setParamData(itemParams);
        itemParamItem.setUpdated(new Date());

        // 更新的条件
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId", itemId);
        return mapper.updateByExampleSelective(itemParamItem, example);
    }
}
