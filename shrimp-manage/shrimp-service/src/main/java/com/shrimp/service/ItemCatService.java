package com.shrimp.service;

import com.shrimp.mapper.ItemCatMapper;
import com.shrimp.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-18
 * Time: 21:38
 */
@Service
public class ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;


    public List<ItemCat> queryItemCatListByPid(Long pid) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(pid);
        return itemCatMapper.select(itemCat);
    }
}
