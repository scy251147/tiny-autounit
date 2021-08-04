package org.tiny.autounit.test;

import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.utils.FileOpsUtil;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-08-03 21:09
 */
public class TestFileOpsUtil {

    @Test
    public void when_readTemplate_then_print_result(){

        String s = FileOpsUtil.readTemplate("template/BaseTemplate");

        System.out.println(s);
        assert s!= null && s.contains("BaseTest") && s.contains("$${template-package-name}$$");

    }

}
