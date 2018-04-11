package com.shrimp.controller;

import com.shrimp.common.bean.EasyUIResult;
import com.shrimp.pojo.Item;
import com.shrimp.pojo.ItemParam;
import com.shrimp.service.ItemParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-10<br>
 */
@Controller
@RequestMapping(value = "item/param")
@Slf4j
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemParamList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "30") int rows) {
        try {
            EasyUIResult easyUIResult = itemParamService.queryItemList(page, rows);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryByItemCatId(@PathVariable(value = "itemCatId") Long itemCatId) {
        try {
            ItemParam record = new ItemParam();
            record.setItemCatId(itemCatId);
            ItemParam param = itemParamService.queryOne(record);
            if (param == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            return ResponseEntity.ok(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "{itemCatId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId") Long itemCatId, @RequestParam("paramData") String paramData) {
        try {
            ItemParam record = new ItemParam();
            record.setId(null);
            record.setItemCatId(itemCatId);
            record.setParamData(paramData);
            itemParamService.save(record);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
