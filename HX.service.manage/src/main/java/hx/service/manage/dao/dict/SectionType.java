package hx.service.manage.dao.dict;

/**
 *@ClassName SectionType
 *@Description 所属部门类型枚举
 *@Author HuoJiaJin
 *@Date 2020/6/26 17:33
 *@Version 1.0
 **/
public enum SectionType {

    SECTION("部", "区域总", 1){},
    GROUP("组", "组经理", 2){},
    ;

    SectionType(String value, String name, int code) {
        this.value = value;
        this.name = name;
        this.code = code;
    }

    private String value;
    private String name;//领导名称
    private int code;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
