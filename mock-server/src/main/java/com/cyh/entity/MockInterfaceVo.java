package com.cyh.entity;

import lombok.Data;

/**
 * @author: yanhua.chen
 * @date: 2021/1/28 15:40
 */
@Data
public class MockInterfaceVo {

    /**
     * 请求路径，如：/public/v1/user/addUser
     */
    private String uri;

    /**
     * 请求方式，如：GET, POST, HEAD, DELETE。支持多个，英文逗号分隔
     */
    private String requestType;

    /**
     * 请求 json body
     */
    private String jsonBody;

    /**
     * 文件内容
     */
    private String mapFileContent;

    /**
     * 响应 json
     */
    private String jsonResponse;

}
