package hx.service.manage.manage.quit;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.quit.QuitApplyStatus;
import hx.base.core.dao.entity.quit.QuitApply;
import hx.base.core.dao.repo.jpa.quit.QuitApplyFlowRepo;
import hx.base.core.dao.repo.jpa.quit.QuitApplyRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.quit.QuitApplyPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.quit.QuitApplyIdRequest;
import hx.service.manage.model.quit.QuitApplyQueryRequest;
import hx.service.manage.model.quit.QuitApplyQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: QuitApplyManagerImpl
 * @Description: 离职申请Manager
 * @Author HuoJiaJin
 * @Date 2021/2/2 23:24
 * @Version 1.0
 **/
@Service
public class QuitApplyManagerImpl extends AbstractManager implements QuitApplyManager{

    @Autowired
    private QuitApplyRepo applyRepo;
    @Autowired
    private QuitApplyFlowRepo applyFlowRepo;

    @Override
    public String query(QuitApplyQueryRequest request){
        CommonResponse response = new CommonResponse();
        QuitApplyPageRequest pageRequest = new QuitApplyPageRequest();
        pageRequest.setAgentCode(request.getEmployeeNum());
        try {
            pageRequest.setStatus(QuitApplyStatus.fromCode(request.getStatus()));
        } catch (InterruptedException e) {
            return response.setError(ErrorType.CONVERT, e.getMessage());
        }
        Pagination page = applyRepo.page(pageRequest);
        page.convertResult(this::convertModel);
        response.setData(page);
        return response.toJson();
    }

    private QuitApplyQueryResponse convertModel(QuitApply entity){
        QuitApplyQueryResponse model = new QuitApplyQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setEmployeeNum(entity.getAgentCode());
        model.setApplyTime(MyTimeTools.timeToStr(entity.getApplyTime()));
        model.setStatus(entity.getStatus().getCode());
        return model;
    }

    @Override
    public String export(QuitApplyIdRequest request){
        //TODO
        return null;
    }
}
