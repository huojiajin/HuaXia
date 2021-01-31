package hx.service.manage.manage.model.honor;

/**
 * @ClassName: HonorImportRequest
 * @Description: 荣誉导入Request
 * @Author HuoJiaJin
 * @Date 2021/2/1 1:29
 * @Version 1.0
 **/
public class HonorImportRequest extends HonorIdRequest {

    private String honorFile;//导入文件

    public String getHonorFile() {
        return honorFile;
    }

    public void setHonorFile(String honorFile) {
        this.honorFile = honorFile;
    }
}
