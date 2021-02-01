package hx.base.core.dao.dict;

/**
 * @name: ErrorType
 * @description: 错误类型
 * @author: huojiajin
 * @time: 2020/5/27 15:33
 */
public enum ErrorType {

    //通用
    VALID(70000, "校验未通过"){},
    CONVERT(70001, "转换出错"){},
    //登录
    VERIFY(70002, "验证码不正确"){},
    LOGIN(70003, "工号或密码错误"){},
    NOLOGIN(70004, "用户未登陆"){},
    PASSWORD(71000, "密码错误"){},
    //系统管理
    USEREXISTS(70005, "用户已存在"){},
    NORESOURCE(70006, "无该菜单权限"){},
    NOUSER(70007, "用户不存在"){},
    NOROLE(70008, "角色不存在"){},
    HASUSER(70009, "该角色下存在在用用户"){},
    //基本法测试
    NOPAPERS(70010, "试卷不存在"){},
    NOIMPORT(70011, "试卷未导入"){},
    NOCOURSE(70012, "学习资料不存在"){},
    //人员管理
    NOTRAIN(70020, "培训场次不存在"){},
    //荣誉
    NOHONOR(70031, "荣誉不存在"){},
    //移动端
    //登录
    NOEMPLAYEE(70100, "客户无法访问本系统"){},
    NOSERVICEPERSON(70101, "内勤功能暂未开放"){},
    //基本法测试
    HASEXPIRE(70110, "该试卷无法作答"){},
    HASSTOP(70111, "该资料已停止使用，无法学习"){},
    HASLEARNED(70112, "已学习过该资料，不获得积分"){},
    HASSIGNUP(70113, "不可重复签到"){},
    HASCOMPLETED(70114, "试卷已作答，不可重复提交"){}
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
