package hx.service.mobile.manage.honor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import hx.base.core.dao.dict.acl.ErrorType;
import hx.base.core.dao.dict.acl.PositionsClass;
import hx.base.core.dao.entity.hualife.RankPromotionTrack;
import hx.base.core.dao.repo.jpa.hualife.RankPromotionTrackRepo;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.MyTimeTools;
import hx.service.mobile.manage.common.AbstractMobileManager;
import hx.service.mobile.model.common.MobileCommonRequest;
import hx.service.mobile.model.honor.RankPromontionTrackResponse;
import hx.service.mobile.model.honor.RankPromontionTrackModel;
import hx.service.mobile.model.login.MobileUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RankPromontionTrackManagerImpl
 * @Description: 职级晋升轨迹ManagerImpl
 * @Author HuoJiaJin
 * @Date 2021/2/6 2:00
 * @Version 1.0
 **/
@Service
public class RankPromontionTrackManagerImpl extends AbstractMobileManager implements RankPromontionTrackManager {

    @Autowired
    private RankPromotionTrackRepo trackRepo;

    @Override
    public String track(MobileCommonRequest request){
        CommonResponse response = new CommonResponse();
        MobileUserModel user = getUser(request.getToken());
        if (user == null) {
            return response.setError(ErrorType.NOLOGIN);
        }
        //查询职级晋升轨迹
        List<RankPromotionTrack> trackList = trackRepo.listByAgetnCode(user.getEmployee_code());
        //处理重复数据
        //创建Map,职级名称+职级起期为key
        Map<String, RankPromotionTrack> trackMaps = Maps.newHashMap();
        for (RankPromotionTrack track : trackList) {
            String key = track.getAgentLastGrade() + track.getStartDate();
            trackMaps.put(key, track);
        }
        List<RankPromotionTrack> newTrackList = Lists.newArrayList(trackMaps.values());
        //按时间正序排序
        newTrackList.sort(Comparator.comparing(RankPromotionTrack::getStartDate));
        //转换返回Model
        List<RankPromontionTrackModel> modelList = Lists.newArrayList();
        for (int i = 0; i < newTrackList.size(); i++) {
            RankPromotionTrack track = newTrackList.get(i);
            RankPromontionTrackModel model = new RankPromontionTrackModel();
            //转换职级
            PositionsClass positionsClass;
            try {
                positionsClass = PositionsClass.valueOf(track.getAgentGrade());
            } catch (Exception e) {
                logger.error("", e);
                continue;
            }
            model.setGradeName(positionsClass.getValue());
            model.setGradeCode(positionsClass.getCode());
            //职级开始时间
            model.setStartTime(MyTimeTools.timeToStr(track.getStartDate(), "yyyy-MM-dd"));
            //职级变更类型
            if (i == 0 && track.getAgentGrade().equals(track.getInitGrade())){//入职
                model.setType(1);
            }
            if (i > 0){
                RankPromotionTrack lastTrack = newTrackList.get(i - 1);
                //转换职级
                PositionsClass lastPositionsClass;
                try {
                    lastPositionsClass = PositionsClass.valueOf(lastTrack.getAgentGrade());
                } catch (Exception e) {
                    logger.error("", e);
                    continue;
                }
                if (lastPositionsClass.getCode() == positionsClass.getCode()){//平调
                    model.setType(4);
                }else if (lastPositionsClass.getCode() < positionsClass.getCode()){//晋升
                    model.setType(2);
                }else if (lastPositionsClass.getCode() > positionsClass.getCode()){//降级
                    model.setType(3);
                }
            }
            modelList.add(model);
        }
        RankPromontionTrackResponse trackResponse = new RankPromontionTrackResponse();
        trackResponse.setResult(modelList);
        response.setData(trackResponse);
        return response.toJson();
    }
}
