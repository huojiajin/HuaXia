package hx.base.core.dao.dict.test;

/**
 * @ClassName CourseType
 * @Description 学习资料类型
 * @Author HuoJiaJin
 * @Date 2020/6/20 21:54
 * @Version 1.0
 **/
public enum CourseType {
    YZYH("应知应会", 1),
    JBF("基本法", 2),
    RXCP("热销产品", 3),
    FAJZD("方案及制度", 4),
    ;


    CourseType(String value, int code) {
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

    public static CourseType fromCode(int code) throws InterruptedException {
        for (CourseType type : CourseType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }
}
