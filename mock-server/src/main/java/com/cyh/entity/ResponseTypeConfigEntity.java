package com.cyh.entity;

import java.util.List;
import lombok.Data;

/**
 * @author: yanhua.chen
 * @date: 2021/1/29 8:58
 */
@Data
public class ResponseTypeConfigEntity {

    // string, integer
    private String type;
    // uuid
    private String generateMethod;
    // 32, 40
    private Integer length;
    // 0-100
    private String range;
    // male, female, boy, girl
    private List<String> values;

}
