package hx.service.manage.quit;

import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.common.CommonTemplateResponse;
import hx.service.manage.model.quit.QuitApplyIdRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * @ClassName: TestQuitApply
 * @Description: 测试离职申请相关
 * @Author HuoJiaJin
 * @Date 2021/3/24 1:49
 * @Version 1.0
 **/
public class TestQuitApply extends ApplicationTests {

    @Test
    void export() throws IOException {

        QuitApplyIdRequest request = new QuitApplyIdRequest();
        request.setToken("0befe1ceb5ea43d1a3b482c3bdfd40a5");
        request.setResourceCode(11);
        request.setApplyId("9077ec4417d3477d839920edb6500d72");
//        String url = "http://39.106.226.73/manage/quit/apply/export";
        String url = "http://localhost/manage/quit/apply/export";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse<CommonTemplateResponse> response = JsonTools.json2Object(s, CommonResponse.class, CommonTemplateResponse.class);
        CommonTemplateResponse templateResponse = response.getData();
        String filePath = "C:\\Users\\huojiajin\\Desktop\\测试.pdf";
        try {
            Files.write(Paths.get(filePath), Base64.getDecoder().decode(templateResponse.getTemplate()), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
