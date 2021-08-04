package org.tiny.autounit.test.biz;

import org.tiny.autounit.sdk.UnitWalk;

import javax.annotation.Resource;

/**
 * @author shichaoyang
 * @Description:
 * @date 2021-07-30 17:13
 */
@UnitWalk
public class UnitBizService implements IUnitBizService {

    @Resource
    private IPriceCalcService priceCalcService;

    public void proecess() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }
}
