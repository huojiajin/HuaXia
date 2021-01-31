package hx.service.manage.manage.honor;

import hx.base.core.dao.entity.honor.Honor;
import hx.base.core.dao.repo.jpa.MarketingManpowerRepo;
import hx.base.core.dao.repo.jpa.honor.HonorPeopleRepo;
import hx.base.core.dao.repo.jpa.honor.HonorRepo;
import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.dao.repo.request.honor.HonorPageRequest;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.manage.manage.AbstractManager;
import hx.service.manage.manage.model.honor.HonorAddRequest;
import hx.service.manage.manage.model.honor.HonorQueryRequest;
import hx.service.manage.manage.model.honor.HonorQueryResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

/**
 * @ClassName: HonorManagerImpl
 * @Description: 荣誉管理ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:23
 * @Version 1.0
 **/
@Service
public class HonorManagerImpl extends AbstractManager implements HonorManager{

    @Autowired
    private MarketingManpowerRepo manpowerRepo;
    @Autowired
    private HonorRepo honorRepo;
    @Autowired
    private HonorPeopleRepo peopleRepo;

    @Override
    public String query(HonorQueryRequest request){
        CommonResponse response = new CommonResponse();
        HonorPageRequest pageRequest = new HonorPageRequest();
        BeanUtils.copyProperties(request, pageRequest);
        Pagination page = honorRepo.page(pageRequest);
        page.convertResult(this::convert);
        response.setData(page);
        return response.toJson();
    }

    private HonorQueryResponse convert(Honor entity){
        HonorQueryResponse model = new HonorQueryResponse();
        model.setName(entity.getName());
        model.setStatus(entity.getStatus().getCode());
        byte[] iconBytes = entity.getIcon();
        String base64 = new String(Base64.encodeBase64(iconBytes));
        model.setIcon(base64);
        model.setCreateTime(MyTimeTools.timeToStr(entity.getInsertTime(), "yyyy-MM-dd"));
        return model;
    }

    @Override
    public String add(HonorAddRequest request){
        CommonResponse response = new CommonResponse();
        Honor honor = new Honor();
        honor.setName(request.getName());
        honor.setIcon(Base64.decodeBase64(request.getIcon()));
        honorRepo.persist(honor);
        honorRepo.blobSave(honor, "icon", new ByteArrayInputStream(Base64.decodeBase64(request.getIcon())));
        addSysLog("添加荣誉", request.getToken(), honor.getId());
        response.setMessage("添加荣誉成功！");
        return response.toJson();
    }
}
