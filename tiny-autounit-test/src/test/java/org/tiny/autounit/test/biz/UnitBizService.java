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

    public void proecess(PriceEntity priceEntity) {
        priceCalcService.calc();
        System.out.println("process work biz");
    }

    public void proecess1() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }

    public void proecess2() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }

    public void proecess3() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }

    public void proecess4() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }

    public void proecess5() {
        priceCalcService.calc();
        System.out.println("process work biz");
    }
}
