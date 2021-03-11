package hx.base.core.manage.model;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @ClassName HXCommonResponse
 * @Description 华夏共同返回response
 * @Author HuoJiaJin
 * @Date 2020/6/26 14:34
 * @Version 1.0
 **/
public class HXCommonResponse<T extends BaseEntity> extends BaseEntity {

    private String code;//结果代码（0成功）
    private String message;//处理结果信息
    private T data;//返回数据结果

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
