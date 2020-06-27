package hx.base.core.dao.dict;

/**
 *@ClassName PositionsClass.java
 *@Description 职级
 *@Author HuoJiaJin
 *@Date 2020/6/18 16:09
 *@Version 1.0
 **/
public enum PositionsClass {
    TC("客户经理"),
    PBC("业务主任"),
    BC("组经理"),
    SBC("高级组经理"),
    BM("区域总经理"),
    SBM("资深总经理"),
    AS("总监"),
    SAS("执行总监"),
    GAS("首席总监"),
    ;

    PositionsClass(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
