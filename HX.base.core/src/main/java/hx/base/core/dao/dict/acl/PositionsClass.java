package hx.base.core.dao.dict.acl;

/**
 *@ClassName PositionsClass.java
 *@Description 职级
 *@Author HuoJiaJin
 *@Date 2020/6/18 16:09
 *@Version 1.0
 **/
public enum PositionsClass {
    TC("客户经理", 1),
    PBC("业务主任", 2),
    BC("组经理", 3),
    SBC("高级组经理", 4),
    BM("区域总经理", 5),
    SBM("资深总经理", 6),
    AS("总监", 7),
    SAS("执行总监", 8),
    GAS("首席总监", 9),
    ;

    PositionsClass(String value, int code) {
        this.value = value;
        this.code = code;
    }

    private String value;
    private int code;

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
