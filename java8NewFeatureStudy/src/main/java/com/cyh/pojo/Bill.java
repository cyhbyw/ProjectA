package com.cyh.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author: yanhua.chen
 * @date: 2021/1/22 16:04
 */
@Data
public class Bill {

    private String type;

    private BigDecimal amount;

    private Date operateTime;

    public Bill(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
        this.operateTime = new Date();
    }
}
