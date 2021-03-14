package hx.base.core.dao.dict.quit;

/**
 * @ClassName: QuitApplyStatus
 * @Description: 离职申请状态
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:10
 * @Version 1.0
 **/
public enum QuitApplyStatus {
    NEW("新申请", 1){},
    NOTAPPROVAL("未审批", 2){},
    APPROVALING("审批中", 3){},
    PASS("审批通过", 4){},
    NOPASS("审批不通过", 5){},
    SPECIAL("特批修改", 6){},
    ;

    QuitApplyStatus(String value, int code) {
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

    public static QuitApplyStatus fromCode(int code) throws InterruptedException {
        for (QuitApplyStatus status : QuitApplyStatus.values()) {
            if (status.getCode() == code){
                return status;
            }
        }
        throw new InterruptedException("此类型不存在：" + code);
    }
}
