package hx.service.manage.manage.acl;

import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.dict.PositionsType;
import hx.service.manage.dao.entity.acl.MobileRoleResource;
import hx.service.manage.dao.repo.jpa.acl.MobileRoleResourceRepo;
import hx.service.manage.manage.common.AbstractManager;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.model.acl.mobile.*;
import hx.service.manage.manage.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: MobileResourceManagerImpl
 * @description: 移动端资源配置ManagerImpl
 * @author: huojiajin
 * @time: 2020/6/18 16:20
 */
@Service
public class MobileResourceManagerImpl extends AbstractManager implements MobileResourceManager{

    @Autowired
    private MobileRoleResourceRepo resourceRepo;

    @Override
    public String listType(CommonRequest request){
        CommonResponse response = new CommonResponse();
        MobileResourceRankListResponse rankResponse = new MobileResourceRankListResponse();
        List<MobileResourceRankResponse> rankList = Arrays.stream(PositionsType.values()).map(c -> {
            MobileResourceRankResponse rank = new MobileResourceRankResponse();
            rank.setRankCode(c.name());
            rank.setRankName(c.getValue());
            return rank;
        }).collect(Collectors.toList());
        rankResponse.setRankList(rankList);
        response.setData(rankResponse);
        return response.toJson();
    }

    @Override
    public String resourceQuery(MobileResourceQueryRequest request){
        PositionsType positionsType = PositionsType.valueOf(request.getRankCode());
        List<MobileRoleResource> roleResources = resourceRepo.listByClass(positionsType);
        List<Integer> resourceCodeList = roleResources.stream().map(m -> m.getResourceCode()).collect(Collectors.toList());
        MobileResourceQueryResponse response = new MobileResourceQueryResponse();
        response.setResourceCodeList(resourceCodeList);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(response);
        return commonResponse.toJson();
    }

    @Override
    @Transactional
    public String resourceConfig(MobileResourceConfigRequest request){
        CommonResponse response = new CommonResponse();
        try {
            PositionsType type = PositionsType.valueOf(request.getRankCode());
            List<MobileRoleResource> entityList = request.getResourceCodeList().stream().map(r -> {
                MobileRoleResource entity = new MobileRoleResource();
                entity.setPositionsType(type);
                entity.setResourceCode(r);
                return entity;
            }).collect(Collectors.toList());
            resourceRepo.deleteByClass(type);
            resourceRepo.persistAll(entityList);
            addSysLog("配置" + type.getValue() + "层级资源代码为" + JsonTools.toJsonStr(request.getResourceCodeList())
                    , request.getToken());
        } catch (Exception e) {
            logger.error("", e);
            return response.setError(ErrorType.CONVERT);
        }
        response.setMessage("配置权限成功");
        return response.toJson();
    }
}