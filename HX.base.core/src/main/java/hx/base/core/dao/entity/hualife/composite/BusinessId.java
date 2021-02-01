package hx.base.core.dao.entity.hualife.composite;

import java.io.Serializable;
import java.util.Objects;

/**
 * @name: BusinessId
 * @description: 业务清单表联合主键
 * @author: huojiajin
 * @time: 2020/6/17 17:51
 */
public class BusinessId implements Serializable {

    private static final long serialVersionUID = 3452269218518286631L;
    private String policyNo;//保单号码
    private String productCode;//险种代码

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof BusinessId){
            BusinessId key = (BusinessId)o ;
            if(this.policyNo.equals(key.getPolicyNo()) && this.productCode.equals(key.getProductCode())){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNo, productCode);
    }
}
