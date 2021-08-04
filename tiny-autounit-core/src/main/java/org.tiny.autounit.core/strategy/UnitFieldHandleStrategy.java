package org.tiny.autounit.core.strategy;

import javassist.CtField;
import lombok.extern.slf4j.Slf4j;
import org.tiny.autounit.core.model.UnitClassMethod;
import org.tiny.autounit.core.model.UnitClassType;
import org.tiny.autounit.core.model.UnitStrategyContent;
import org.tiny.autounit.core.model.context.UnitInjectModel;
import org.tiny.autounit.core.model.context.UnitMockContext;
import org.tiny.autounit.core.utils.RegexUtil;

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
        stringBuilder.append(RegexUtil.newTab()).append("private " + RegexUtil.getClassName(classFullName) + " " + RegexUtil.getClassVariableName(classFullName)).append(RegexUtil.newLine());
        stringBuilder.append(RegexUtil.newLine());

        //添加到上下文，方便后面取用
        if(unitMockContext == null){
            unitMockContext = new UnitMockContext();
        }
        UnitInjectModel unitInjectModel = new UnitInjectModel();
        unitInjectModel.setClassName(RegexUtil.getClassVariableName(classFullName));
        unitMockContext.setUnitInjectModel(unitInjectModel);

        StringBuilder importBuilder = new StringBuilder();

        for (CtField declaredField : declaredFields) {
            //校验注入标签
            if (checkAnnotation(declaredField)) {
                //生成mock内容
                stringBuilder.append(RegexUtil.newTab()).append("@Mock").append("\n");
                stringBuilder.append(RegexUtil.newTab()).append("private " + RegexUtil.getClassName(declaredField.getFieldInfo().getDescriptor().replace(";", ""), "/") + " " + declaredField.getFieldInfo().getName());
                //生成import内容
                importBuilder.append("import " + RegexUtil.getClassPath(declaredField.getFieldInfo().getDescriptor(), "/").replace("/", ".") + ";");
            }
        }

        //组装并返回
        UnitStrategyContent content = new UnitStrategyContent();
        content.getContent().put(UnitClassType.inject_field, stringBuilder);
        content.getContent().put(UnitClassType.import_path, importBuilder);
        return content;
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
