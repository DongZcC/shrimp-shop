package com.shrimp.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shrimp.web.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-16<br>
 */
@Service
public class ItemService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_MANAGE_URL}")
    private String TAOTAO_MANAGE_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Item queryItemById(Long itemId) {
        String url = TAOTAO_MANAGE_URL + "rest/api/item" + itemId;
        try {
            String jsonData = apiService.doGet(url);
            return MAPPER.readValue(jsonData, Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
