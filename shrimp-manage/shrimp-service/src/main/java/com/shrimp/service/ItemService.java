package com.shrimp.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.pojo.Item;
import com.shrimp.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-24<br>
 */
@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    ItemDescService itemDescService;

    public void saveItem(Item item, String desc) {
        //保存item
        save(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDescService.save(itemDesc);
    }

    public EasyUIResult queryItemList(Integer page, Integer rows) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, rows);
        // Example example = new Example(Item.class);
        Example example = new Example(Item.class);
        example.setOrderByClause("updated DESC");
        List<Item> items = mapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 更新商品数据，和desc
     * @param item
     * @param desc
     * @return
     */
    public boolean updateItem(Item item, String desc) {
        // 强制设置不允许更新的字段
        item.setCreated(null);
        item.setStatus(null);
        item.setUpdated(new Date());
        int f1 = updateSelective(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        int f2 = itemDescService.updateSelective(itemDesc);
        return f1 == 1 && f2 == 1;
    }
}
