package hx.service.manage.model.staff.train;

/**
 * @name: TrainImportRequest
 * @description: 参训人员管理人员导入Request
 * @author: huojiajin
 * @time: 2020/11/12 11:43
 */
public class TrainImportRequest extends TrainIdRequest{

    private String trainFile;//培训人员文件

    public String getTrainFile() {
        return trainFile;
    }

    public void setTrainFile(String trainFile) {
        this.trainFile = trainFile;
    }
}
