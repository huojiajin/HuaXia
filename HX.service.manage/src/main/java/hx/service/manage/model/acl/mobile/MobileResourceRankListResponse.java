package hx.service.manage.model.acl.mobile;

import hx.base.core.dao.entity.common.BaseEntity;

import java.util.List;

/**
 * @ClassName MobileResourceRankListResponse
 * @Description 移动端职级列表Model
 * @Author HuoJiaJin
 * @Date 2020/6/20 14:42
 * @Version 1.0
 **/
public class MobileResourceRankListResponse extends BaseEntity {

    private List<MobileResourceRankResponse> rankList;

    public List<MobileResourceRankResponse> getRankList() {
        return rankList;
    }

    public void setRankList(List<MobileResourceRankResponse> rankList) {
        this.rankList = rankList;
    }
}
