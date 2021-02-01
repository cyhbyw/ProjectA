package com.cyh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyh.consts.MockConstants;
import com.cyh.entity.MockInterfaceVo;
import com.cyh.entity.ResponseTypeConfigEntity;
import com.cyh.utils.UuidUtils;
import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class MockServerController {

    private List<MockInterfaceVo> mockList = new ArrayList<>();
    private ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    @PostMapping("/addMockInterface")
    public String addMockInterface(@RequestBody MockInterfaceVo vo, MultipartFile file) throws Exception {
        // 参数传入的 FileContent 优先级高于文件的
        if (StringUtils.isBlank(vo.getMapFileContent())) {
            vo.setMapFileContent(getFileContent(file));
        }
        mockList.add(vo);
        return "SUCCESS";
    }

    private String getFileContent(MultipartFile file) throws Exception {
        final byte[] bytes = file.getBytes();
        if (Objects.isNull(bytes) || bytes.length == 0) {
            return null;
        }
        return new String(bytes);
    }

    @PutMapping("/modifyMockInterface")
    public void modifyMockInterface() {
    }

    @GetMapping("/getMockInterface")
    public void getMockInterface() {
    }

    @DeleteMapping("/deleteMockInterface")
    public void deleteMockInterface() {
    }

    @RequestMapping(value = "/mock/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String mock(HttpServletRequest request) {
        threadLocal.set(request);

        final String uri = request.getRequestURI().substring(5); /**    /mock    */
        MockInterfaceVo vo = mockList.stream().filter(x -> x.getUri().equals(uri)).findFirst().get();
        if (Objects.isNull(vo)) {
            throw new RuntimeException(String.format("路径%s没有设置Mock", uri));
        }

        final String result = vo.getJsonResponse();
        JSONObject jsonObject = JSON.parseObject(result);
        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            final String key = entry.getKey();
            final Object currentValue = entry.getValue();
            final Object newValue = changeValueWhenNeeded(key, currentValue);
            jsonObject.put(key, newValue);
            log.info("key={}, currentValue={}, newValue={}", key, currentValue, newValue);
        }
        return JSON.toJSONString(jsonObject);
    }

    private Object changeValueWhenNeeded(String key, Object value) {
        if (value instanceof JSONObject) {
            return handleObject((JSONObject) value);
        } else if (value instanceof JSONArray) {
            JSONArray array = (JSONArray) value;
            int size = array.size();
            for (int i = 0; i < size; i++) {
                array.set(i, changeValueWhenNeeded("", array.get(i)));
            }
            return value;
        } else {
            return tryReplace(key, value);
        }
    }

    private Object handleObject(JSONObject value) {
        for (Entry<String, Object> entry : value.entrySet()) {
            final String key = entry.getKey();
            final Object entryValue = entry.getValue();
            final Object newValue = changeValueWhenNeeded(key, entryValue);
            value.put(key, newValue);
        }
        return value;
    }

    private Object tryReplace(String key, Object value) {
        value = tryHandleCustomizedResponseType(value);
        value = tryHandleMapFileResponseType(key, value);
        return value;
    }

    private Object tryHandleCustomizedResponseType(Object value) {
        if (!(value instanceof String)) {
            return value;
        }
        String value2 = (String) value;
        if (!(value2.startsWith(MockConstants.CUSTOMIZED_RESPONSE_TYPE))) {
            return value;
        }

        value2 = value2.substring(MockConstants.CUSTOMIZED_RESPONSE_TYPE.length());
        List<String> configList = split(MockConstants.SP, value2);
        ResponseTypeConfigEntity config = new ResponseTypeConfigEntity();
        for (String temp : configList) {
            final List<String> keyValue = split(MockConstants.SP_KV, temp);
            final String configKey = keyValue.get(0);
            final String configValue = keyValue.get(1);
            if ("type".equalsIgnoreCase(configKey)) {
                config.setType(configValue);
            } else if ("generateMethod".equalsIgnoreCase(configKey)) {
                config.setGenerateMethod(configValue);
            } else if ("length".equalsIgnoreCase(configKey)) {
                config.setLength(Integer.valueOf(configValue));
            } else if ("range".equalsIgnoreCase(configKey)) {
                config.setRange(configValue);
            } else if ("values".equalsIgnoreCase(configKey)) {
                config.setValues(split(MockConstants.SP_COMMA, configValue));
            }
        }
        if ("string".equalsIgnoreCase(config.getType()) && "uuid".equalsIgnoreCase(config.getGenerateMethod())) {
            String uuid = Objects.isNull(config.getLength()) ? UuidUtils.upperNoLine()
                : UuidUtils.upperNoLine(config.getLength());
            return uuid;
        } else if ("integer".equalsIgnoreCase(config.getType())) {
            final List<String> rangeList = split(MockConstants.SP_RANGE, config.getRange());
            final Integer min = Integer.valueOf(rangeList.get(0));
            final Integer max = Integer.valueOf(rangeList.get(1));
            return new Random().nextInt(max - min) + min;
        } else if ("enum".equalsIgnoreCase(config.getType())) {
            int size = config.getValues().size();
            return config.getValues().get(new Random().nextInt(size));
        }

        return value;
    }

    private Object tryHandleMapFileResponseType(String key, Object value) {
        if (!(value instanceof String)) {
            return value;
        }
        String value2 = (String) value;
        if (!(value2.startsWith(MockConstants.MAP_FILE_RESPONSE_TYPE))) {
            return value;
        }

        value2 = value2.substring(MockConstants.MAP_FILE_RESPONSE_TYPE.length());
        final List<String> keyValue = split(MockConstants.SP_KV, value2);
        final String configKey = keyValue.get(0);
        final String configValue = keyValue.get(1);
        List<String> requestParams = new ArrayList<>();
        if ("requestParams".equalsIgnoreCase(configKey)) {
            requestParams = split(MockConstants.SP_COMMA, configValue);
        }
        JSONObject paramObject = new JSONObject();
        for (String param : requestParams) {
            String paramValue = threadLocal.get().getParameter(param);
            paramObject.put(param, paramValue);
        }
        final String uri = threadLocal.get().getRequestURI().substring(5); /**    /mock    */
        MockInterfaceVo vo = mockList.stream().filter(x -> x.getUri().equals(uri)).findFirst().get();
        final String fileContent = vo.getMapFileContent();
        List<String> fileLines = split("\n", fileContent);
        for (String line : fileLines) {
            final List<String> requestResponse = split(MockConstants.SP_FILE_CONTENT, line);
            JSONObject requestJson = JSON.parseObject(requestResponse.get(0));
            if (Objects.equals(paramObject, requestJson)) {
                log.info("suitable response found: {}", requestResponse.get(1));
                JSONObject responseJson = JSON.parseObject(requestResponse.get(1));
                return responseJson.get(key);
            }
        }

        log.info("paramObject={} fileContent={}", paramObject, fileContent);

        return null;
    }

    private List<String> split(String sp, String value) {
        return Splitter.on(sp).trimResults().omitEmptyStrings().splitToList(value);
    }


}
