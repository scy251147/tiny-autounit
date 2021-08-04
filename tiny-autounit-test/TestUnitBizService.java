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


public class TestUnitBizService extends BaseTest{

     @Before
     public void init(){
         MockitoAnnotations.initMocks(this);
     }

    @InjectMocks
    private UnitBizService unitBizService

    @Mock
    private IPriceCalcService priceCalcService

    &&{{method-body}}&&

}
