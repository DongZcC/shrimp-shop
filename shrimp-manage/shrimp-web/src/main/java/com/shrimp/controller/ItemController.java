package com.shrimp.controller;

import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.pojo.Item;
import com.shrimp.pojo.ItemDesc;
import com.shrimp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-24<br>
 */
@RequestMapping("item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item, @RequestParam(value = "desc") String desc) {
        item.setStatus(1);
        item.setId(null); // ID 由数据库自动生成
        //为了解决 事务问题，因此商品的增加 ，与商品描述的增加必须都要放到 同一个service方法中， 这样才能被事务管理到
        try {
            itemService.saveItem(item, desc);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemList(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            EasyUIResult easyUIResult = itemService.queryItemList(page, rows);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
