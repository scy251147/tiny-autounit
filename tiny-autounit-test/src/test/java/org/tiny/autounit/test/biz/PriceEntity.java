package org.tiny.autounit.test.biz;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-04 15:05
 */
@Data
public class PriceEntity {

    private String name;

    private String code;

    private BigDecimal money;

}
