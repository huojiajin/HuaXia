package hx.service.manage.dao.dict;

/**
 * @ClassName PapersSubjectType
 * @Description 试卷题目类型
 * @Author HuoJiaJin
 * @Date 2020/6/21 13:58
 * @Version 1.0
 **/
public enum PapersSubjectType {
    SINGLE("单选", 1),
    MULTIPLE("多选", 2),
    ;

    PapersSubjectType(String value, int code) {
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

    public static PapersSubjectType fromCode(int code) throws InterruptedException {
        for (PapersSubjectType type : PapersSubjectType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("无此类型" + code);
    }
}
