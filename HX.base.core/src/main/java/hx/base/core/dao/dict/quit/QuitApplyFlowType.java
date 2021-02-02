package hx.base.core.dao.dict.quit;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName: QuitApplyFlowType
 * @Description: 离职申请审批流程类别
 * @Author HuoJiaJin
 * @Date 2021/2/2 0:34
 * @Version 1.0
 **/
public enum QuitApplyFlowType {

    NONEXECUTIVE("非主管离职审批流程", "quitApplyTemplate.pdf"){
        @Override
        public List<QuitApplyApprovalType> getApprovalFlow() {
            return Lists.newArrayList(QuitApplyApprovalType.RECOMMEND, QuitApplyApprovalType.GROUP, QuitApplyApprovalType.SECTION);
        }
    },
    GROUPMANAGER("组经理离职审批流程", "executiveQuitApplyTemplate.pdf"){
        @Override
        public List<QuitApplyApprovalType> getApprovalFlow() {
            return Lists.newArrayList(QuitApplyApprovalType.REAR, QuitApplyApprovalType.SECTION, QuitApplyApprovalType.CHIEF);
        }
    },
    SECTIONMANAGER("部经理离职审批流程", "executiveQuitApplyTemplate.pdf"){
        @Override
        public List<QuitApplyApprovalType> getApprovalFlow() {
            return Lists.newArrayList(QuitApplyApprovalType.REAR, QuitApplyApprovalType.CHIEF);
        }
    },
    ;

    QuitApplyFlowType(String value, String template) {
        this.value = value;
        this.template = template;
    }

    private String value;
    private String template;//模板名称

    public String getValue() {
        return value;
    }

    public String getTemplate() {
        return template;
    }

    public abstract List<QuitApplyApprovalType> getApprovalFlow();

    /**
     * @Name getNextApprovalType
     * @Author HuoJiaJin
     * @Description 获取下一个返回流程，如果为空则表示流程结束
     * @Date 2021/2/2 0:46
     * @Param [flowType, currentApprovalType]
     * @Return hx.base.core.dao.dict.quit.QuitApplyApprovalType
     **/
    public static QuitApplyApprovalType getNextApprovalType(QuitApplyFlowType flowType, QuitApplyApprovalType currentApprovalType) throws InterruptedException {
        List<QuitApplyApprovalType> approvalFlow = flowType.getApprovalFlow();
        int index = -1;
        for (int i = 0; i < approvalFlow.size(); i++) {
            if (approvalFlow.get(i) == currentApprovalType){
                index = i + 1;
            }
        }
        if (index >= approvalFlow.size()){//如果是最后一个流程，则返回空
            return null;
        }else if (index == -1){//如果未找到流程
            throw new InterruptedException("审批流程错误！");
        }else{
            return approvalFlow.get(index);
        }
    }
}
