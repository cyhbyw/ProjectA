package com.cyh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyh.entity.MockInterfaceVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping("/mock/**")
    public String mock(HttpServletRequest request) {
        final String uri = request.getRequestURI().substring(5);
        String result = mockList.stream().filter(x -> x.getUri().equals(uri)).findFirst().map(x -> x.getJsonResponse())
            .orElseThrow(() -> new RuntimeException(String.format("路径%s没有设置Mock", uri)));
        JSONObject jsonObject = JSON.parseObject(result);
        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            final String key = entry.getKey();
            final Object currentValue = entry.getValue();
            final Object newValue = dfs(currentValue);

            log.info("key={}, currentValue={}, newValue={}", key, currentValue, newValue);
        }
        return "Done";
    }

    private Object dfs(Object value) {
        if (value instanceof JSONObject) {
            return handleObject(value);
        } else if (value instanceof JSONArray) {
            JSONArray array = (JSONArray) value;
            int size = array.size();
            for (int i = 0; i < size; i++) {
                array.set(i, dfs(array.get(i)));
            }
            return value;
        } else {
            return value;
        }
    }

    private Object handleObject(Object value) {
        return value;
    }


}
