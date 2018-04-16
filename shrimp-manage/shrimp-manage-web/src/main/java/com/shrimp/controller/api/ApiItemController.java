package com.shrimp.controller.api;

import com.shrimp.pojo.Item;
import com.shrimp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-16<br>
 */
@Controller
@RequestMapping("api/item")
public class ApiItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> queryById(@PathVariable("itemId") Long itemId) {
        try {
            Item item = itemService.queryById(itemId);
            if (item == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
