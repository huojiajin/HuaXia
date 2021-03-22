package hx.service.manage.manage.quit;

import hx.base.core.dao.entity.quit.QuitAssign;
import hx.base.core.dao.repo.jpa.quit.QuitAssignRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.quit.QuitAssignPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.quit.QuitAssignEditRequest;
import hx.service.manage.model.quit.QuitAssignQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: QuitAssignManagerImpl
 * @Description: 离职人员指派ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/1 23:31
 * @Version 1.0
 **/
@Service
public class QuitAssignManagerImpl extends AbstractManager implements QuitAssignManager {

    @Autowired
    private QuitAssignRepo assignRepo;

    @Override
    public String query(CommonPageRequest request){
        CommonResponse response = new CommonResponse();
        QuitAssignPageRequest pageRequest = new QuitAssignPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = assignRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private QuitAssignQueryResponse convert(QuitAssign entity){
        QuitAssignQueryResponse model = new QuitAssignQueryResponse();
        model.setId(entity.getId());
        model.setType(entity.getType().getCode());
        model.setEmployeeNum(entity.getAgentCode());
        model.setName(entity.getName());
        model.setCampName(entity.getCampName());
        model.setCampCode(entity.getCampCode());
        return model;
    }

    @Override
    public String edit(QuitAssignEditRequest request){
        CommonResponse response = new CommonResponse();
        assignRepo.updatePeople(request.getName(), request.getEmployeeNum(), request.getId());
        response.setMessage("修改指派人员成功！");
        addSysLog("修改指派人员为" + request.getName(), request.getToken(), request.getId());
        return response.toJson();
    }
}
