package hx.base.core.dao.dict.salary;

import com.google.common.collect.Lists;
import hx.base.core.dao.dict.acl.PositionsClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PrizeType.java
 * @Description: 奖项类别
 * @Author HuoJiaJin
 * @Date 2021/2/7 2:57
 * @Version 1.0
**/
public enum PrizeType {

    //创业发展奖
    CHIEFBUSINESS("区创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.AS.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //初始化结果
            BigDecimal result;
            //直辖组13J不足85%，津贴按照50%发放
            double rate = 1;
            Double continueRate = influenceMaps.get(PrizeInfluenceType.CONTINUERATE);
            if (continueRate == null){
                throw new InterruptedException(this.getValue() + "需要继续率指标");
            }
            if (continueRate < 0.85){
                rate = 0.5;
            }
            //计算具体数据
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            result = new BigDecimal(String.valueOf(FYC)).multiply(new BigDecimal(String.valueOf(rate)));
            if (FYC >= 60000 && FYC < 120000){
                result = result.multiply(new BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if (FYC >= 120000 && FYC < 240000){
                result = result.multiply(new BigDecimal("0.015")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if (FYC >= 240000){
                result = result.multiply(new BigDecimal("0.02")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else {
                throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
            }
            return result.doubleValue();
        }
    },
    SECTIONBUSINESS("部创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.BM.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //初始化结果
            BigDecimal result;
            //直辖组13J不足85%，津贴按照50%发放
            double rate = 1;
            Double continueRate = influenceMaps.get(PrizeInfluenceType.CONTINUERATE);
            if (continueRate == null){
                throw new InterruptedException(this.getValue() + "需要继续率指标");
            }
            if (continueRate < 0.85){
                rate = 0.5;
            }
            //计算具体数据
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            result = new BigDecimal(String.valueOf(FYC)).multiply(new BigDecimal(String.valueOf(rate)));
            if (FYC >= 30000 && FYC < 60000){
                if (position.getCode() == PositionsClass.BM.getCode()){
                    result = result.multiply(new BigDecimal("0.08")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BM.getCode()) {
                    result = result.multiply(new BigDecimal("0.09")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else if (FYC >= 60000 && FYC < 120000){
                if (position.getCode() == PositionsClass.BM.getCode()){
                    result = result.multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BM.getCode()) {
                    result = result.multiply(new BigDecimal("0.14")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else if (FYC >= 120000){
                if (position.getCode() == PositionsClass.BM.getCode()){
                    result = result.multiply(new BigDecimal("0.17")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BM.getCode()) {
                    result = result.multiply(new BigDecimal("0.18")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else {
                throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
            }
            return result.doubleValue();
        }
    },
    GROUPBUSINESS("组创业发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE, PrizeInfluenceType.ATTENDANCE);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.BC.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //初始化结果
            BigDecimal result;
            //直辖组13J不足85%，津贴按照50%发放
            double rate = 1;
            Double continueRate = influenceMaps.get(PrizeInfluenceType.CONTINUERATE);
            if (continueRate == null){
                throw new InterruptedException(this.getValue() + "需要继续率指标");
            }
            if (continueRate < 0.85){
                rate = 0.5;
            }
            //主管本人当月有效出勤不足15天（入职首月工作有效出勤率≥80%），组创业发展奖按照70%发放；
            Double attendanceNum = influenceMaps.get(PrizeInfluenceType.ATTENDANCE);
            double attendanceRate = 1;
            if (attendanceNum >= 15){
                attendanceRate = 0.7;
            }
            //计算具体数据
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            result = new BigDecimal(String.valueOf(FYC)).multiply(new BigDecimal(String.valueOf(rate))).multiply(new BigDecimal(String.valueOf(attendanceRate)));
            if (FYC >= 6000 && FYC < 15000){
                if (position.getCode() == PositionsClass.BC.getCode()){
                    result = result.multiply(new BigDecimal("0.15")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BC.getCode()) {
                    result = result.multiply(new BigDecimal("0.16")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else if (FYC >= 15000 && FYC < 30000){
                if (position.getCode() == PositionsClass.BC.getCode()){
                    result = result.multiply(new BigDecimal("0.25")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BC.getCode()) {
                    result = result.multiply(new BigDecimal("0.26")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else if (FYC >= 30000){
                if (position.getCode() == PositionsClass.BC.getCode()){
                    result = result.multiply(new BigDecimal("0.35")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }else if (position.getCode() > PositionsClass.BC.getCode()) {
                    result = result.multiply(new BigDecimal("0.36")).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }else {
                throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
            }
            return result.doubleValue();
        }
    },

    //人力发展奖
    CHIEFMANPOWER("区人力发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.AS.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //计算具体数据
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            if (health >= 50 && health < 90){
                return 5000d;
            }else if (health >= 90 && health < 130){
                return 8000d;
            }else if (health >= 130){
                return 12000d;
            }else {
                throw new InterruptedException("健康人力为" + health + ",未达到获奖标准");
            }
        }
    },
    SECTIONMANPOWER("部人力发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH, PrizeInfluenceType.FYC);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position != PositionsClass.BM && position != PositionsClass.SBM){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //校验影响因素
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            //计算具体数据
            Double result;
            boolean isBM = position == PositionsClass.BM;
            if (health >= 8 && health < 15){
                result = isBM ? 1000d : 1100d;
            }else if (health >= 15 && health < 25){
                result = isBM ? 1400d : 1600d;
            }else if (health >= 25){
                result = isBM ? 2800d : 3000d;
            }else {
                throw new InterruptedException("健康人力为" + health + ",未达到获奖标准");
            }
            if (FYC >= 1500){
                result = result * 2;
            }
            return result;
        }
    },
    GROUPMANPOWER("组人力发展奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH, PrizeInfluenceType.FYC);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position != PositionsClass.BC && position != PositionsClass.SBC){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //校验影响因素
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            //计算具体数据
            Double result;
            boolean isBC = position == PositionsClass.BC;
            if (health == 3){
                result = isBC ? 500d : 600d;
            }else if (health >= 4 && health < 8){
                result = isBC ? 700d : 800d;
            }else if (health >= 8){
                result = isBC ? 1400d : 1600d;
            }else {
                throw new InterruptedException("健康人力为" + health + ",未达到获奖标准");
            }
            if (FYC >= 1500){
                result = result * 2;
            }
            return result;
        }
    },

    //卓越部组成就奖
    EXCELLENTSECTION("卓越部成就奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH, PrizeInfluenceType.FYC);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.BM.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //校验影响因素
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            //计算具体数据
            if (health >= 15){
                if (FYC >= 200000 && FYC < 400000){
                    return 20000d;
                }else if (FYC >= 400000){
                    return 40000d;
                }else {
                    throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
                }
            }else {
                throw new InterruptedException("健康人力为" + health + ",未达到获奖标准");
            }
        }
    },
    EXCELLENTGROUP("卓越组成就奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH, PrizeInfluenceType.FYC);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验职级
            if (position.getCode() < PositionsClass.BC.getCode()){
                throw new InterruptedException("职级" + position.getValue() + "无" + this.getValue());
            }
            //校验影响因素
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            //计算具体数据
            BigDecimal result;
            if (health >= 4){
                if (FYC >= 60000){
                    result = new BigDecimal(String.valueOf(FYC)).multiply(new BigDecimal("0.1")).setScale(2, BigDecimal.ROUND_HALF_UP);
                    return result.doubleValue() > 10000 ? 10000 : result.doubleValue();
                }else {
                    throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
                }
            }else {
                throw new InterruptedException("健康人力为" + health + ",未达到获奖标准");
            }
        }
    },

    //个人半年奖 每个人都要算，单独计算，每年1月、7月计算
    INDIVIDUALHALFYEAR("个人半年奖"){
        @Override
        public List<PrizeInfluenceType> getInfluenceTypes() {
            return Lists.newArrayList(PrizeInfluenceType.HEALTH, PrizeInfluenceType.FYC, PrizeInfluenceType.CONTINUERATE);
        }

        @Override
        public Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException {
            //校验影响因素
            Double health = influenceMaps.get(PrizeInfluenceType.HEALTH);
            if (health == null){
                throw new InterruptedException(this.getValue() + "需要健康人力指标");
            }
            Double FYC = influenceMaps.get(PrizeInfluenceType.FYC);
            if (FYC == null){
                throw new InterruptedException(this.getValue() + "需要FYC指标");
            }
            Double continueRate = influenceMaps.get(PrizeInfluenceType.CONTINUERATE);
            if (continueRate == null){
                throw new InterruptedException(this.getValue() + "需要继续率指标");
            }
            //个人继续率不足70%，无获取资格；个人继续率不足80%，按照50%发放；个人继续率不足85%按照70%发放。
            double rate = 1;
            if (continueRate >= 0.7 && continueRate < 0.8){
                rate = 0.5;
            }else if (continueRate >= 0.8 && continueRate < 0.85){
                rate = 0.7;
            }else if (continueRate < 0.7){
                throw new InterruptedException("个人继续率为" + continueRate + ",未达到获奖标准");
            }
            //计算具体数据
            BigDecimal result = new BigDecimal(String.valueOf(FYC)).multiply(new BigDecimal(rate));
            if (FYC >= 5000 && FYC < 7000){
                result = result.multiply(new BigDecimal("0.15")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if (FYC >= 7000 && FYC < 10000){
                result = result.multiply(new BigDecimal("0.20")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if (FYC >= 10000 && FYC < 30000){
                result = result.multiply(new BigDecimal("0.25")).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if (FYC >= 30000){
                result = result.multiply(new BigDecimal("0.32")).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                throw new InterruptedException("FYC为" + FYC + ",未达到获奖标准");
            }
            return result.doubleValue();
        }
    },
    ;

    PrizeType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public abstract List<PrizeInfluenceType> getInfluenceTypes();

    public abstract Double calculateSalary(Map<PrizeInfluenceType, Double> influenceMaps, PositionsClass position) throws InterruptedException;
}
