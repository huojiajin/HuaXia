package hx.service.mobile.manage.back;

import com.google.common.collect.Lists;
import hx.base.core.dao.repo.jpa.hualife.MarketingManpowerRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.back.org.*;
import hx.service.mobile.model.common.MobileCommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BackManagerImpl
 * @Description: 内勤相关ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/3/14 15:02
 * @Version 1.0
 **/
@Service
public class BackManagerImpl extends AbstractMobileManager implements BackManager {

    @Autowired
    private MarketingManpowerRepo manpowerRepo;

    @Override
    public String getCampListBack(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        BackCampListResponse data = new BackCampListResponse();
        List<BackCampModel> campList = Lists.newArrayList();
        List<Map<String, String>> campMaps = manpowerRepo.groupByCamp();
        for (Map<String, String> campMap : campMaps) {
            BackCampModel model = new BackCampModel();
            model.setCampName(campMap.get("campName"));
            model.setCampCode(campMap.get("campCode"));
            campList.add(model);
        }
        data.setCampList(campList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getDirectorListBack(BackDirectorListRequest request){
        CommonResponse response = new CommonResponse();
        BackDirectorListResponse data = new BackDirectorListResponse();
        List<BackDirectorModel> directorList = Lists.newArrayList();
        List<Map<String, String>> directorMaps;
        if (hasText(request.getCampCode())) {
            directorMaps = manpowerRepo.groupByDirector(request.getCampCode());
        }else {//如果未传营服，则查询所有总监区
            directorMaps = manpowerRepo.groupByDirectorAll();
        }
        for (Map<String, String> directorMap : directorMaps) {
            BackDirectorModel model = new BackDirectorModel();
            model.setDirectorName(directorMap.get("directorName"));
            model.setDirectorCode(directorMap.get("directorCode"));
            directorList.add(model);
        }
        data.setDirectorList(directorList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getSectionListBack(BackSectionListRequest request){
        CommonResponse response = new CommonResponse();
        BackSectionListResponse data = new BackSectionListResponse();
        List<BackSectionModel> sectionList = Lists.newArrayList();
        List<Map<String, String>> sectionMaps = manpowerRepo.groupBySection(request.getDirectorCode());
        for (Map<String, String> sectionMap : sectionMaps) {
            BackSectionModel model = new BackSectionModel();
            model.setSectionName(sectionMap.get("sectionName"));
            model.setSectionCode(sectionMap.get("sectionCode"));
            sectionList.add(model);
        }
        data.setSectionList(sectionList);
        response.setData(data);
        return response.toJson();
    }

    @Override
    public String getGroupListBack(BackGroupListRequest request){
        CommonResponse response = new CommonResponse();
        BackGroupListResponse data = new BackGroupListResponse();
        List<BackGroupModel> groupList = Lists.newArrayList();
        List<Map<String, String>> groupMaps = manpowerRepo.groupByGroup(request.getSectionCode());
        for (Map<String, String> groupMap : groupMaps) {
            BackGroupModel model = new BackGroupModel();
            model.setGroupName(groupMap.get("groupName"));
            model.setGroupCode(groupMap.get("groupCode"));
            groupList.add(model);
        }
        data.setGroupList(groupList);
        response.setData(data);
        return response.toJson();
    }
}
