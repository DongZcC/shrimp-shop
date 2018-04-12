package com.shrimp.common.spring.extend.converter.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 功能说明: 对于springmvc的messageconverter进行扩展<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-12<br>
 */
public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {


    // 添加对于jsonp的支持
    private String callbackName;

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    // 重写
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        // 从threadlocal中获取当前的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String callback = request.getParameter(callbackName);
        if (StringUtils.isEmpty(callback))
            super.writeInternal(object, outputMessage);
        else {
            // 进行jsonp的支持
            // 获取编码
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            try {
                String result = callback + "(" + super.getObjectMapper().writeValueAsString(object) + ");";
                IOUtils.write(result, outputMessage.getBody(), encoding.getJavaName());
            } catch (JsonProcessingException e) {
                throw new HttpMessageNotWritableException("Could not write Json: " + e.getMessage(), e);
            }
        }
    }
}
