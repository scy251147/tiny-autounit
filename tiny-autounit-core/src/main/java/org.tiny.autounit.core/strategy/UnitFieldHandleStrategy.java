package org.tiny.autounit.core.strategy;

import javassist.CtField;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.model.context.UnitInjectModel;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.model.context.UnitMockModel;
import org.tiny.autounit.core.utils.RegexUtil;

import java.lang.reflect.Field;

/**
 * @author shichaoyang
 * @Description: 处理注入部分
 * @date 2021-07-30 17:30
 */
@Slf4j
public class UnitFieldHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {

        CtField[] declaredFields = unitClassMethod.getCtClass().getDeclaredFields();

        String classFullName = unitClassMethod.getCtClass().getName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@InjectMocks").append(RegexUtil.newLine());
        stringBuilder.append(RegexUtil.new4Tab()).append("private " + RegexUtil.getClassName(classFullName) + " " + RegexUtil.getClassVariableName(classFullName)).append(";").append(RegexUtil.newLine());
        stringBuilder.append(RegexUtil.newLine());

        //添加到上下文，方便后面取用
        fillInjectMocksInfo2Context(unitMockContext, classFullName);

        StringBuilder importBuilder = new StringBuilder();

        //生成field模板内容
        for (CtField declaredField : declaredFields) {
            //校验注入标签
            if (checkAnnotation(declaredField)) {
                //生成mock内容
                stringBuilder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append("@Mock").append(RegexUtil.newLine());
                String mockClassName = RegexUtil.getClassName(declaredField.getFieldInfo().getDescriptor().replace(";", ""), "/");
                String mockVariaName = declaredField.getFieldInfo().getName();
                stringBuilder.append(RegexUtil.new4Tab()).append("private " + mockClassName + " " + mockVariaName).append(";");
                //生成import内容
                importBuilder.append("import " + RegexUtil.getClassPath(declaredField.getFieldInfo().getDescriptor(), "/").replace("/", ".") + ";");
                //添加到上下文
                fillMocksInfo2Context(unitClassMethod.getClazz(), unitMockContext, RegexUtil.getClassName(mockVariaName));
            }
        }

        //组装并返回
        UnitStrategyContent content = new UnitStrategyContent();
        content.getContent().put(UnitClassType.inject_field, stringBuilder);
        content.getContent().put(UnitClassType.import_path, importBuilder);
        return content;
    }

    /**
     * 添加上下文信息， @InjectMocks
     * @param unitMockContext
     * @param injectClassFullName
     */
    private void fillInjectMocksInfo2Context(UnitMockContext unitMockContext, String injectClassFullName) {
        if (unitMockContext == null) {
            unitMockContext = new UnitMockContext();
        }
        //设置injectMocks
        UnitInjectModel unitInjectModel = new UnitInjectModel();
        unitInjectModel.setClassName(RegexUtil.getClassVariableName(injectClassFullName));
        unitMockContext.setUnitInjectModel(unitInjectModel);
    }

    /**
     * 添加上下文信息， @Mock
     * @param unitMockContext
     * @param mockClassName
     */
    private void fillMocksInfo2Context(Class clazz, UnitMockContext unitMockContext, String mockClassName) {

        //设置mocks
        UnitMockModel unitMockModel = new UnitMockModel();
        unitMockModel.setClassName(mockClassName);

        //设置class
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(mockClassName)) {
                unitMockModel.setClazz(field.getType());
                break;
            }
        }

        unitMockContext.getUnitMockModelList().add(unitMockModel);
    }

    /**
     * 校验spring的@Resource或者@Autowired标签，以便于使结果更准确
     *
     * @param declaredField
     * @return
     */
    private boolean checkAnnotation(CtField declaredField) {
        //TODO 最好检查下spring @Resource和@Autowired标签，会更准确
        return true;
    }
}
