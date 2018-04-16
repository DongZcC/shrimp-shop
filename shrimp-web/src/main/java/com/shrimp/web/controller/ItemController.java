package com.shrimp.web.controller;

import com.shrimp.web.pojo.Item;
import com.shrimp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-16<br>
 */
@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("itemId") Long itemId) {
        ModelAndView mv = new ModelAndView("item");
        Item item = itemService.queryItemById(itemId);
        mv.addObject("item", item);
        return mv;
    }
}
