package hx.service.mobile;

import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.mobile.manage.model.test.course.CourseDetailRequest;
import hx.service.mobile.manage.model.test.course.CourseDetailResponse;
import hx.service.mobile.manage.model.test.course.CourseListRequest;
import hx.service.mobile.manage.model.test.paper.PaperCompletedListRequest;
import hx.service.mobile.manage.model.test.paper.PaperListRequest;
import hx.service.mobile.manage.model.test.paper.PaperSubmitRequest;
import hx.service.mobile.manage.model.test.paper.PaperSubmitSubjectModel;
import org.apache.commons.compress.utils.Lists;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName IndexTest
 * @Description 首页Test
 * @Author HuoJiaJin
 * @Date 2020/6/27 19:23
 * @Version 1.0
 **/
public class PaperTest extends ApplicationTests{

    private static final String token = "882fdfe7018d4f2ba092fabaee438b8a";

    @Test
    public void query() throws IOException {
        String url = "http://localhost:81/mobile/test/paper/list";

        PaperListRequest request = new PaperListRequest();
        request.setToken("0b274c829cac4dba8972ce886ad98b6d");
        request.setType(5);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }


    @Test
    public void getSubmit() throws IOException {
        String url = "http://localhost:81/mobile/test/paper/submit";
//        String url = "http://123.56.154.176:81/mobile/test/paper/submit";
        PaperSubmitRequest request = new PaperSubmitRequest();
        request.setToken(token);
        request.setPaperId("613d8862bf86476a9480665bac63bb1b");
        List<PaperSubmitSubjectModel> modelList = Lists.newArrayList();
        PaperSubmitSubjectModel model = new PaperSubmitSubjectModel();
        model.setSubjectId("d8ea9f306d024ac4a456be1eaa03640b");
        model.setAnswer("1");
        modelList.add(model);
        PaperSubmitSubjectModel model2 = new PaperSubmitSubjectModel();
        model2.setSubjectId("48106b70369e4ecf9c64a08d6c02b325");
        model2.setAnswer("1|2");
        modelList.add(model2);
        request.setSubjectList(modelList);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    public void getCompleteList() throws IOException {
        String url = "http://localhost:81/mobile/test/paper/completed/list";

        PaperCompletedListRequest request = new PaperCompletedListRequest();
        request.setToken("0b274c829cac4dba8972ce886ad98b6d");
        request.setType(1);
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }
}
