package hx.service.mobile.model.structure.internal;

import hx.base.core.dao.entity.common.BaseEntity;

/**
 * @name: StructureDirectorModel
 * @description: 组织架构-获取相关总监区列表Model
 * @author: huojiajin
 * @time: 2021/2/25 11:52
 */
public class StructureDirectorModel extends BaseEntity {

    private String directorCode;//总监区代码
    private String directorName;//总监区名称

    public String getDirectorCode() {
        return directorCode;
    }

    public void setDirectorCode(String directorCode) {
        this.directorCode = directorCode;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
