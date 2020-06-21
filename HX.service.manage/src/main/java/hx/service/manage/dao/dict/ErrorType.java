package hx.service.manage.dao.dict;

/**
 * @name: ErrorType
 * @description: 错误类型
 * @author: huojiajin
 * @time: 2020/5/27 15:33
 */
public enum ErrorType {

    //通用
    CONVERT(70001, "转换出错"){},
    //登录
    VERIFY(70002, "验证码不正确"){},
    LOGIN(70003, "用户名或密码错误"){},
    NOLOGIN(70004, "用户未登陆"){},
    //系统管理
    USEREXISTS(70005, "用户已存在"){},
    NORESOURCE(70006, "无该菜单权限"){},
    NOUSER(70007, "用户不存在"){},
    NOROLE(70008, "角色不存在"){},
    HASUSER(70009, "该角色下存在在用用户"){},
    //基本法测试
    NOPAPERS(70010, "试卷不存在"){},
    NOOPTION(70011, "选项小于两个"){},
    SCORE(70012, "分值不正确"){},
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
