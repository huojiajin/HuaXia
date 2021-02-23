package hx.base.core.dao.dict.salary;

/**
 * @ClassName: PrizeGroupType
 * @Description: 薪资项目部门类别
 * @Author HuoJiaJin
 * @Date 2021/2/22 0:19
 * @Version 1.0
 **/
public enum PrizeGroupType {
    CAMP("区", 1){},
    SECTION("部", 2){},
    GROUP("组", 3){},
    PERSON("个人", 4){},
    ;

    PrizeGroupType(String value, int list) {
        this.value = value;
        this.list = list;
    }

    private String value;
    private int list;//排序

    public String getValue() {
        return value;
    }

    public int getList() {
        return list;
    }
}
