package hx.base.core.dao.dict.share;

/**
 *@ClassName ShareType.java
 *@Description 分享数据类型
 *@Author HuoJiaJin
 *@Date 2021/3/11 15:31
 *@Version 1.0
 **/
public enum ShareType {
    ACCESSTOKEN("华夏总部accessToken");

    ShareType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
