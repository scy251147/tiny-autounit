package org.tiny.autounit.test;

import org.junit.jupiter.api.Test;
import org.tiny.autounit.core.UnitBootStrap;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-07-28 17:41
 */
public class TestUnitBootStrap {

    @Test
    public void bootstrapTest(){
        UnitBootStrap unitBootStrap = new UnitBootStrap();
        unitBootStrap.start("org.tiny.autounit.test.biz");
    }
}
