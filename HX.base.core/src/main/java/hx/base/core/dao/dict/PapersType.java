package hx.base.core.dao.dict;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName PapersType
 * @Description 试卷类型
 * @Author HuoJiaJin
 * @Date 2020/6/20 21:54
 * @Version 1.0
 **/
public enum PapersType {
    YZYH("应知应会", 1, 1),
    JBF("基本法", 2, 1),
    RXCP("热销产品", 3, 1),
    FAJZD("方案及制度", 4, 1),
    ZZRZRZ("资质认证-入职", 51, 2),
    ZZRZZJLJJ("资质认证-组经理晋级", 52, 2),
    ZZRZBJLJJ("资质认证-部经理晋级", 53, 2),
    ZZRZPX("资质认证-培训", 54, 2),
    ;


    PapersType(String value, int code, int level) {
        this.value = value;
        this.code = code;
        this.level = level;
    }

    private String value;
    private int code;
    private int level;

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public int getLevel() {
        return level;
    }

    public static List<PapersType> getZZRZ(){
        return Lists.newArrayList(ZZRZRZ, ZZRZZJLJJ, ZZRZBJLJJ, ZZRZPX);
    }

    public static PapersType fromCode(int code) throws InterruptedException {
        for (PapersType type : PapersType.values()) {
            if (type.getCode() == code){
                return type;
            }
        }
        throw new InterruptedException("无此状态" + code);
    }
}
