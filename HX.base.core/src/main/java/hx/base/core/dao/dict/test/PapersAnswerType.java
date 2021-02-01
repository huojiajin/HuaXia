package hx.base.core.dao.dict.test;

/**
 *@ClassName PapersAnswerType
 *@Description 试卷回答状态
 *@Author HuoJiaJin
 *@Date 2020/6/22 0:01
 *@Version 1.0
 **/
public enum PapersAnswerType {
    WDT("未答题", 1){},
    YDT("已答题", 2){},
    ;

    PapersAnswerType(String value, int code) {
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

    public static PapersAnswerType fromCode(int code) throws InterruptedException {
        for (PapersAnswerType type : PapersAnswerType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }
}
