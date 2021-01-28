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
     * 响应 json
     */
    private String jsonResponse = "{\n"
        + "    \"data\": [\n"
        + "        {\n"
        + "            \"generateId\": \"customizedResponseType:type=string,generateMethod=uuid,bit=32\",\n"
        + "            \"changecodeId\": \"606873578402480128\",\n"
        + "            \"aid\": \"2222\",\n"
        + "            \"redeemcode\": \"UBQ2KZIXNDKSD\",\n"
        + "            \"user\": {\n"
        + "                \"name\": \"customizedResponseType:type=string,generateMethod=uuid,bit=10\",\n"
        + "                \"age\": 30,\n"
        + "                \"lover\": [\n"
        + "                    {\n"
        + "                        \"name\": \"customizedResponseType:type=string,generateMethod=uuid,bit=5\"\n"
        + "                    }\n"
        + "                ]\n"
        + "            }\n"
        + "        }\n"
        + "    ],\n"
        + "    \"data2\": {\n"
        + "        \"one\": [\n"
        + "            \"a\",\n"
        + "            \"b\",\n"
        + "            \"c\"\n"
        + "        ],\n"
        + "        \"two\": {\n"
        + "            \"two_one\": \"customizedResponseType:type=string,generateMethod=uuid,bit=5\"\n"
        + "        }\n"
        + "    },\n"
        + "    \"returnStatus\": \"SUCCESS\",\n"
        + "    \"returnStatusCode\": \"CDP.TICKET.200\",\n"
        + "    \"returnMessage\": \"OK\"\n"
        + "}\n";

}
