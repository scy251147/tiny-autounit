package org.tiny.autounit.test.biz;

import org.tiny.autounit.sdk.UnitExeclude;
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

    @Resource
    private ISubmitOrderService submitOrderService;

    public String proecess(PriceEntity priceEntity) {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
        OrderModel orderModel = new OrderModel();
        submitOrderService.submit(orderModel);
        return "";
    }

    public void proecess1() {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
    }

    public void proecess2() {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
    }

    public void proecess3() {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
    }

    @UnitExeclude
    public void proecess4() {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
    }

    public void proecess5() {
        priceCalcService.calc("sss");
        System.out.println("process work biz");
    }
}
