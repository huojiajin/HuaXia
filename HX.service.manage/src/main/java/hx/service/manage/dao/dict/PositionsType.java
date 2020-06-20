package hx.service.manage.dao.dict;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @name: PositionsType
 * @description: 职级分类
 * @author: huojiajin
 * @time: 2020/6/18 16:27
 */
public enum PositionsType {
    FZG("非主管"){
        @Override
        public List<PositionsClass> getPositionsClass() {
            return Lists.newArrayList(PositionsClass.TC, PositionsClass.PBC);
        }
    },
    BC("组经理"){
        @Override
        public List<PositionsClass> getPositionsClass() {
            return Lists.newArrayList(PositionsClass.BC, PositionsClass.SBC);
        }
    },
    BM("区域总经理"){
        @Override
        public List<PositionsClass> getPositionsClass() {
            return Lists.newArrayList(PositionsClass.BM, PositionsClass.SBM);
        }
    },
    AS("总监") {
        @Override
        public List<PositionsClass> getPositionsClass() {
            return Lists.newArrayList(PositionsClass.AS, PositionsClass.SAS, PositionsClass.GAS);
        }
    },
    ;

    PositionsType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public abstract List<PositionsClass> getPositionsClass();
}
