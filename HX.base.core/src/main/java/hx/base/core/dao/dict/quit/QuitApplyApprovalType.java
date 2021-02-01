package hx.base.core.dao.dict.quit;

/**
 * @ClassName: QuitApplyApprovalType
 * @Description: 离职申请审批类别
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:24
 * @Version 1.0
 **/
public enum QuitApplyApprovalType {
    RECOMMEND("推荐人审批"){},
    GROUP("组经理审批"){},
    SECTION("区域总审批"){},
    REAR("一代组育成人审批"){},
    CHIEF("总监审批"){},
    ;

    QuitApplyApprovalType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
