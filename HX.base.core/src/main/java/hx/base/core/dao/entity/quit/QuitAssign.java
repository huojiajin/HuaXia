package hx.base.core.dao.entity.quit;

import hx.base.core.dao.entity.common.AbstractInsertTimeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @name: QuitAssign
 * @description: 离职指派实体
 * @author: huojiajin
 * @time: 2021/2/1 16:36
 */
@Entity
@Table(name = "hx_quit_assign")
public class QuitAssign extends AbstractInsertTimeEntity {

    private String agentCode;//内勤人员编码 FIXME 从哪个表里获取？
//    private String
}
