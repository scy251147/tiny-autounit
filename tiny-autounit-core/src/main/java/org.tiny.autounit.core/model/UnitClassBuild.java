package org.tiny.autounit.core.model;

import lombok.Data;

/**
 * @author shichaoyang
 * @Description: 测试内容实体相关
 * @date 2021-07-30 15:47
 */
@Data
public class UnitClassBuild {

    private String packageName;

    private String className;

    private String content;

}
