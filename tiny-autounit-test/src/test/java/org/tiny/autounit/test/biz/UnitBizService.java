package org.tiny.autounit.test.biz;

import org.tiny.autounit.sdk.UnitWalk;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-07-30 17:13
 */
@UnitWalk
public class UnitBizService implements IUnitBizService {

    public void proecess() {
        System.out.println("process work biz");
    }
}
