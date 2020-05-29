package hx.service.manage.dao.dict;

/**
 * @name: ErrorType
 * @description: 错误类型
 * @author: huojiajin
 * @time: 2020/5/27 15:33
 */
public enum ErrorType {

    CONVERT(70001, "转换出错"){},
    VERIFY(70002, "验证码不正确"){},
    LOGIN(70003, "用户名或密码错误"){},
    NOLOGIN(70004, "用户未登陆"){},
    USEREXISTS(70005, "用户已存在"){},
    NORESOURCE(70006, "无该菜单权限"){},
    ;

    private final int errCode;
    private final String errMsg;

    private ErrorType(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
