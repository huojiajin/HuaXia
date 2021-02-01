package hx.base.core.dao.dict.quit;

/**
 * @ClassName: QuitAssignType
 * @Description: 离职申请指派人员类型
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:02
 * @Version 1.0
 **/
public enum QuitAssignType {
    ZX("组训", 1){},
    YFJL("营服经理", 2){},
    FGSRG("分公司人管", 3){},
    ;

    QuitAssignType(String value, int code) {
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
