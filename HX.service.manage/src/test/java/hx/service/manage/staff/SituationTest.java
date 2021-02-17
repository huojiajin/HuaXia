package hx.service.manage.staff;

import hx.base.core.dao.repo.request.common.Pagination;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.common.CommonExportResponse;
import hx.service.manage.model.staff.situation.SituationQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @name: SituationTest
 * @description: 人员情况统计Test
 * @author: huojin
 * @time: 2020/6/22 10:16
 */
public class SituationTest extends ApplicationTests {

    private static final String token = "5718a71270b942d4918ccdf12914704b";

    @Test
    void query() throws IOException {
        SituationQueryRequest request = new SituationQueryRequest();
        request.setToken(token);
        request.setResourceCode(11);
        request.setPageNo(1);
        request.setPageSize(20);
        request.setCampCode("861102010");
        request.setCampName("北京本级营业区第一营业区");
//        String url = "http://123.56.154.176/manage/staff/situation/query";
        String url = "http://localhost/manage/staff/situation/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse response = JsonTools.json2Object(s, CommonResponse.class, Pagination.class);
        echo(response);
        if (response.isSuccess()){
            echo(response.getData());
        }
    }


    @Test
    void export() throws IOException {
        SituationQueryRequest request = new SituationQueryRequest();
        request.setToken(token);
        request.setResourceCode(21);
        request.setCampCode("861102010");
        request.setCampName("北京本级营业区第一营业区");
        request.setGroupName("天创部直辖组");
//        String url = "http://123.56.154.176/manage/staff/situation/export";
        String url = "http://localhost/manage/staff/situation/export";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse<CommonExportResponse> response =
                JsonTools.json2Object(responseStr, CommonResponse.class, CommonExportResponse.class);
        echo(response);
        byte[] b = Base64Utils.decodeFromString(response.getData().getResultFile());
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }

        OutputStream out = new FileOutputStream("C:\\Users\\huojiajin\\Desktop\\人员情况统计.xlsx");
        out.write(b);
        out.flush();
        out.close();
    }
}
