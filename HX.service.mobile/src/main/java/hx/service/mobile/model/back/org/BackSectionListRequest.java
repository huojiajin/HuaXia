package hx.service.mobile.model.back.org;

import hx.service.mobile.model.common.MobileCommonRequest;

/**
 * @name: RadarSectionListRequest
 * @description: 雷达图-获取相关部列表Request
 * @author: huojiajin
 * @time: 2021/2/25 12:07
 */
public class BackSectionListRequest extends MobileCommonRequest {

    private String directorCode;//总监区代码

    public String getDirectorCode() {
        return directorCode;
    }

    public void setDirectorCode(String directorCode) {
        this.directorCode = directorCode;
    }
}
