package org.tiny.autounit.core.model;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shichaoyang
 * @Description: 策略使用
 * @date 2021-08-04 12:01
 */
@Data
public class UnitStrategyContent {

    /**
     * 类型对应的内容
     */
    private Map<UnitClassType, StringBuilder> content = new HashMap<>();

}
