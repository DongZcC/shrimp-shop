package com.shrimp.controller;

import com.shrimp.pojo.ItemCat;
import com.shrimp.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-18
 * Time: 21:23
 */
@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCatList(@RequestParam(value = "id", defaultValue = "0") Long pid) {
        try {
            ItemCat record = new ItemCat();
            record.setParentId(pid);
            List<ItemCat> cats = itemCatService.queryListByWhere(record);
            if (cats == null || cats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping( value = "/info",method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryItemCat(@RequestParam(value = "id")Long cid) {
        try {
            ItemCat cat = itemCatService.queryById(cid);
            if (cat == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.ok(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
