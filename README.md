# tiny-autounit

### Yet another java auto unit testing generation tool.

It's tiny, no need to import other dependencies, jar size totally less than 30kb.


### Class settings:

For a single class, you can put `@UnitWalk` annotation on top of it to make the class generates the unit testing class automatically. 

If any methods that you don't want to generate it, then `@UnitExeclude` on this method can rescue. 

Sample code below:

```java

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
```

Sample above is easy to use for small project really, But when our project is large and adding `@UnitWalk` on top of the classes will bring us nightmare.
Consider this, this structure provides `@UnitScan` annotation to specific the scan package. When the project bootstraps, then the structure will scan the package in `@UnitScan` annotation to work.


After the settings, we can bootstrap the structure by following code:

```java
        UnitBootStrap unitBootStrap = new UnitBootStrap();
        unitBootStrap.start("org.tiny.autounit.test.biz");

```
Need to mention that , the package name in above code is the package that will fill into the generated unit test class. 


Putting above bootstrap code into main entrace and start the project, then we can get the automated generated unit class below:

```java

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
       when(submitOrderService.submit(Mockito.any())).thenReturn(Mockito.any());
       when(priceCalcService.calc(Mockito.any())).thenReturn(Mockito.any());
       
       PriceEntity unitBizService = new PriceEntity();
       unitBizService.setName("testData");
       unitBizService.setCode("testData");
       unitBizService.setMoney(0d);


       String returnResult = unitBizService.proecess(priceEntity,orderModel);
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


```

This structure is still under design, any advice or push will be appreciated. 
