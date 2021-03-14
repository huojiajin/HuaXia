package hx.service.mobile.web.back;

import hx.base.core.manage.common.CommonAbstract;
import hx.service.mobile.manage.back.SalaryManager;
import hx.service.mobile.model.back.salary.SalaryQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SalaryController
 * @Description: 薪资项目预警（追踪支持）Controller
 * @Author HuoJiaJin
 * @Date 2021/3/13 15:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/salaryAlert")
public class SalaryController extends CommonAbstract {

    @Autowired
    private SalaryManager manager;

    @PostMapping("/query")
    public String query(@RequestBody SalaryQueryRequest request){
        return manager.query(request);
    }
}
