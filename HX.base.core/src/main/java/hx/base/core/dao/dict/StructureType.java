package hx.base.core.dao.dict;

/**
 *@ClassName StructureType
 *@Description 组织架构标准类型
 *@Author HuoJiaJin
 *@Date 2020/7/6 17:32
 *@Version 1.0
 **/
public enum StructureType {
    ZCRL("在册人力", "直辖部在册{}人、直辖组在册{}人", "在册人力", "直辖组在册{}人", 0){},
    CQRL("出勤人力", "直辖部出勤{}人、常勤{}人", "出勤人力", "直辖组出勤{}人、常勤{}人", 1){},
    JYRL("绩优人力", "直辖部四星以上{}人、MDRT{}人", "绩优人力", "直辖组四星以上{}人、MDRT{}人", 2){},
    ZYZS("直育组数", "直接育成组{}个，间接育成{}个", "直增人数", "直接增员{}个，间接增员{}个", 3){},
    SDRL("实动人力", "直辖部实动人力{}个，直辖组实动{}人", "实动人力", "直辖组实动{}人", 4){},
    YXZS("月新增数", "直辖部当月新增{}人，直辖组新增{}人", "月新增数", "直辖组新增{}人", 5){},
    ZGJY("主管绩优", "主管本人{}星，{}达成MDRT", "主管绩优", "主管本人{}星，{}达成MDRT", 6){},
    ;

    StructureType(String sectionName, String sectionSituation, String groupName, String groupSituation, int code) {
        this.sectionName = sectionName;
        this.sectionSituation = sectionSituation;
        this.groupName = groupName;
        this.groupSituation = groupSituation;
        this.code = code;
    }

    private String sectionName;//部指标名称
    private String sectionSituation;//部现状语句
    private String groupName;//组指标名称
    private String groupSituation;//组现状语句
    private int code;//编码

    public String getSectionName() {
        return sectionName;
    }

    public String getSectionSituation() {
        return sectionSituation;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupSituation() {
        return groupSituation;
    }

    public int getCode() {
        return code;
    }

    public static StructureType fromCode(int code) throws InterruptedException {
        for (StructureType type : StructureType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }
}
