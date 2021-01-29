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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MockServerController {

    private List<MockInterfaceVo> mockList = new ArrayList<>();

    @PostMapping("/addMockInterface")
    public String addMockInterface(@RequestBody MockInterfaceVo vo) {
        // duplication check
        mockList.add(vo);
        return "SUCCESS";
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
        final String uri = request.getRequestURI().substring(5);
        String result = mockList.stream().filter(x -> x.getUri().equals(uri)).findFirst().map(x -> x.getJsonResponse())
            .orElseThrow(() -> new RuntimeException(String.format("路径%s没有设置Mock", uri)));
        JSONObject jsonObject = JSON.parseObject(result);
        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            final String key = entry.getKey();
            final Object currentValue = entry.getValue();
            final Object newValue = changeValueWhenNeeded(currentValue);
            log.info("key={}, currentValue={}, newValue={}", key, currentValue, newValue);
        }
        return JSON.toJSONString(jsonObject);
    }

    private Object changeValueWhenNeeded(Object value) {
        if (value instanceof JSONObject) {
            return handleObject((JSONObject) value);
        } else if (value instanceof JSONArray) {
            JSONArray array = (JSONArray) value;
            int size = array.size();
            for (int i = 0; i < size; i++) {
                array.set(i, changeValueWhenNeeded(array.get(i)));
            }
            return value;
        } else {
            return tryReplace(value);
        }
    }

    private Object handleObject(JSONObject value) {
        for (Entry<String, Object> entry : value.entrySet()) {
            final String key = entry.getKey();
            final Object entryValue = entry.getValue();
            final Object newValue = changeValueWhenNeeded(entryValue);
            value.put(key, newValue);
        }
        return value;
    }

    private Object tryReplace(Object value) {
        if (!(value instanceof String)) {
            return value;
        }

        String value2 = (String) value;
        if (!(value2.startsWith(MockConstants.CUSTOMIZED_RESPONSE_TYPE))) {
            return value;
        }

        value2 = value2.substring(MockConstants.CUSTOMIZED_RESPONSE_TYPE.length());
        List<String> configList = Splitter.on(MockConstants.SP).trimResults().omitEmptyStrings().splitToList(value2);
        ResponseTypeConfigEntity config = new ResponseTypeConfigEntity();
        for (String temp : configList) {
            final List<String> keyValue = Splitter.on(MockConstants.SP_KV).splitToList(temp);
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
                config.setValues(
                    Splitter.on(MockConstants.SP_COMMA).trimResults().omitEmptyStrings().splitToList(configValue));
            }
        }
        if ("string".equalsIgnoreCase(config.getType()) && "uuid".equalsIgnoreCase(config.getGenerateMethod())) {
            String uuid = Objects.isNull(config.getLength()) ? UuidUtils.upperNoLine()
                : UuidUtils.upperNoLine(config.getLength());
            return uuid;
        } else if ("integer".equalsIgnoreCase(config.getType())) {
            final List<String> rangeList = Splitter.on(MockConstants.SP_RANGE).splitToList(config.getRange());
            final Integer min = Integer.valueOf(rangeList.get(0));
            final Integer max = Integer.valueOf(rangeList.get(1));
            return new Random().nextInt(max - min) + min;
        } else if ("enum".equalsIgnoreCase(config.getType())) {
            int size = config.getValues().size();
            return config.getValues().get(new Random().nextInt(size));
        }

        return value;
    }


}
