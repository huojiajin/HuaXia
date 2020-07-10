package hx.base.core.dao.dict;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *@name: MobileResourceType
 *@description: 手机菜单枚举
 *@author: huojiajin
 *@time: 2020/5/26 15:21
**/
public enum MobileResourceType {

    A_JHSY("京华书院", 1, 1, null) {},
    B_JGJY("架构经营", 2, 1, null) {},
    C_XZKH("薪资考核", 3, 1, null) {},
    D_RYQ("荣誉墙", 4, 1, null) {},
    E_RYGL("人员管理", 5, 1, null) {},
    E_RYGL_KHSR("客户生日", 51, 2, E_RYGL) {},
    E_RYGL_RYSR("人员生日", 52, 2, E_RYGL) {},
    E_RYGL_RYQK("人员情况统计", 53, 2, E_RYGL) {},
    ;

	private final String value;
	private final int code;
	private final int level;//从1开始，1为最高
	private final MobileResourceType type;//从属于哪个菜单

	private MobileResourceType(String value, int code, int level, MobileResourceType type) {
		this.value = value;
		this.code = code;
		this.level = level;
		this.type = type;
	}


	public String getValue() {
		return value;
	}

    public int getCode() {
        return code;
    }

    public int getLevel() {
        return level;
    }

    public MobileResourceType getType() {
        return type;
    }

    public static List<MobileResourceType> getLevelOneResourceType(){
        List<MobileResourceType> typeList = Lists.newArrayList();
        MobileResourceType[] typsArr = MobileResourceType.values();
        for (MobileResourceType type : typsArr) {
            if (type.getLevel() == 1){
                typeList.add(type);
            }
        }
        return typeList;
    }

    public static List<MobileResourceType> getLevelTwoResourceType(MobileResourceType classAType){
        List<MobileResourceType> typeList = Lists.newArrayList();
        MobileResourceType[] typsArr = MobileResourceType.values();
        for (MobileResourceType type : typsArr) {
            if (type.getLevel() == 2){
                typeList.add(type);
            }
        }
        return typeList;
    }

    private static MobileResourceType fromCode(int code) throws InterruptedException {
        MobileResourceType[] typsArr = MobileResourceType.values();
        for (MobileResourceType type : typsArr) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("此类型不存在" + code);
    }
}
