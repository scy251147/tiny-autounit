package org.tiny.autounit.core.model.context;

import lombok.Data;

import java.util.List;

/**
 * @author shichaoyang
 * @Description: 上下文共享信息, 一个测试类，会有一个InjectMocks，多个Mock，多个Spy
 * @date 2021-08-04 16:33
 */
@Data
public class UnitMockContext {

    //injectmocks信息
    private UnitInjectModel unitInjectModel;

    //mock列表信息
    private List<UnitMockModel> unitMockModelList;

    //spy列表信息
    private List<UnitSpyModel> unitSpyModelList;

}
