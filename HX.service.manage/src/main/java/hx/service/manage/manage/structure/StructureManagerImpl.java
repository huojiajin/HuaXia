package hx.service.manage.manage.structure;

import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.entity.structure.StructureStandard;
import hx.base.core.dao.repo.jpa.structure.StructureStandardRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.structure.StructureStandardPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.model.common.CommonPageRequest;
import hx.service.manage.model.structure.StructureEditRequest;
import hx.service.manage.model.structure.StructureQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName: StructureManagerImpl
 * @Description: 组织架构ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/24 1:15
 * @Version 1.0
 **/
@Service
public class StructureManagerImpl extends AbstractManager implements StructureManager{

    @Autowired
    private StructureStandardRepo standardRepo;

    @Override
    public String query(CommonPageRequest request){
        CommonResponse response = new CommonResponse();
        StructureStandardPageRequest pageRequest = new StructureStandardPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = standardRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private StructureQueryResponse convert(StructureStandard entity){
        StructureQueryResponse model = new StructureQueryResponse();
        BeanUtils.copyProperties(entity, model);
        model.setSectionType(entity.getSectionType().getCode());
        model.setRateType(entity.getRateType().getCode());
        model.setStructureType(entity.getStructureType().getCode());
        return model;
    }

    @Override
    public String edit(StructureEditRequest request){
        CommonResponse response = new CommonResponse();
        Optional<StructureStandard> op = standardRepo.findById(request.getId());
        if (op.isPresent()){
            StructureStandard standard = op.get();
            BeanUtils.copyProperties(request, standard, new String[]{"id"});
            standardRepo.save(standard);
            addSysLog("修改组织架构标准为" + standard.toJson(), request.getToken(), standard.getId());
        }else{
            return response.setError(ErrorType.CONVERT, "该标准不存在");
        }
        response.setMessage("修改组织架构标准成功");
        return response.toJson();
    }
}
