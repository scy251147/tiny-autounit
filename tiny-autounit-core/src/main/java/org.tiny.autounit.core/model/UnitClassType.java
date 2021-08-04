package org.tiny.autounit.core.model;

/**
 * @author shichaoyang
 * @Description: 类组成类型
 * @date 2021-08-04 12:05
 */
public enum UnitClassType {

    template_package_name("$${template-package-name}$$", "包名"),
    import_path("$${import-path}$$", "import路径"),
    test_class_name("$${test-class-name}$$", "测试类名"),
    inject_field("$${inject-field}$$", "Mock对象"),
    method_body("&&{{method-body}}&&", "整个方法体"),
    test_method_name("$${test-method-name}$$", "方法名"),
    test_method_body("$${test-method-body}$$", "方法体");

    UnitClassType(String expr, String desc) {
        this.expr = expr;
        this.desc = desc;
    }

    //表达式模板
    private String expr;

    //描述
    private String desc;


    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
