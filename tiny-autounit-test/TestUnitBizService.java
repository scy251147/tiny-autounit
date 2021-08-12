package org.tiny.autounit.test.biz;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.mock;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.stubbing.Answer;
import org.tiny.autounit.test.biz;import org.tiny.autounit.test.biz;

public class TestUnitBizService extends BaseTest{

     @Before
     public void init(){
         MockitoAnnotations.initMocks(this);
     }

    @InjectMocks
    private UnitBizService unitBizService;


    @Mock
    private IPriceCalcService priceCalcService;
    @Mock
    private ISubmitOrderService submitOrderService;

    @Test
    public void when_proecess_then_return_success(){
       when(submitOrderService.submit(Mockito.any(),Mockito.any())).thenReturn(Mockito.any());
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       PriceEntity unitBizService = new PriceEntity();
       unitBizService.setName("testData");
       unitBizService.setCode("testData");
       unitBizService.setMoney(0d);


       String returnResult = unitBizService.proecess(priceEntity,orderModel,testModel);
       assert returnResult != null;
    }

    @Test
    public void when_proecess1_then_return_success(){
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       

       unitBizService.proecess1();
    }

    @Test
    public void when_proecess2_then_return_success(){
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       

       unitBizService.proecess2();
    }

    @Test
    public void when_proecess3_then_return_success(){
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       

       unitBizService.proecess3();
    }

    @Test
    public void when_proecess5_then_return_success(){
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       

       unitBizService.proecess5();
    }


}
