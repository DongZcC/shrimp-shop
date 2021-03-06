package com.shrimp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.common.bean.ItemCatData;
import com.shrimp.common.bean.ItemCatResult;
import com.shrimp.mapper.ItemCatMapper;
import com.shrimp.pojo.ItemCat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-18
 * Time: 21:38
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String REDIS_ITEMCAT_KEY = "TAOTAO_MANAGE_ITEM_CAT_ALL"; // 定义key规则

    private static final int REDIS_EXPIRE_TIME = 60 * 24;

    public ItemCatResult queryAllToTree() {
        try {
            String cache = redisService.get(REDIS_ITEMCAT_KEY);
            // 命中
            if (StringUtils.isNotEmpty(cache)) {
                return MAPPER.readValue(cache, ItemCatResult.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemCatResult itemCatResult = new ItemCatResult();
        List<ItemCat> itemCats = queryAll();

        Map<Long, List<ItemCat>> itemCatMap = new HashMap<>();
        // 分好组
        for (ItemCat itemCat : itemCats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }

        // 封装一级对象
        List<ItemCat> itemCats1 = itemCatMap.get(0l);
        for (ItemCat itemCat : itemCats1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            itemCatResult.getItemCats().add(itemCatData);
            if (!itemCat.getIsParent())
                continue;

            // 封装二级对象
            List<ItemCat> itemCats2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData1 = new ArrayList<>();
            itemCatData.setItems(itemCatData1);
            for (ItemCat cat : itemCats2) {
                ItemCatData itemCatData2 = new ItemCatData();
                itemCatData2.setUrl("/products/" + cat.getId() + ".html");
                itemCatData2.setName(cat.getName());
                itemCatData1.add(itemCatData2);
                if (cat.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatList3 = itemCatMap.get(cat.getId());
                    List<String> itemCatData3 = new ArrayList<String>();
                    itemCatData2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        itemCatData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
                    }
                }
            }

            if (itemCatResult.getItemCats().size() >= 14) {
                break;
            }
        }

        // 将结果写入到缓存中
        try {
            redisService.set(REDIS_ITEMCAT_KEY, MAPPER.writeValueAsString(itemCatResult), REDIS_EXPIRE_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemCatResult;
    }
}
