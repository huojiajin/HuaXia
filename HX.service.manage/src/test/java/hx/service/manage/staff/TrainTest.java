package hx.service.manage.staff;

import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.ApplicationTests;
import hx.service.manage.model.common.CommonExportResponse;
import hx.service.manage.model.common.CommonRequest;
import hx.service.manage.model.common.CommonTemplateResponse;
import hx.service.manage.model.staff.train.TrainAddRequest;
import hx.service.manage.model.staff.train.TrainImportRequest;
import hx.service.manage.model.staff.train.TrainPeopleQueryRequest;
import hx.service.manage.model.staff.train.TrainQueryRequest;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * @name: TrainTest
 * @description: 参训人员管理Test
 * @author: huojin
 * @time: 2020/6/22 10:16
 */
public class TrainTest extends ApplicationTests {

    private static final String token = "82f2dba491394eecac35415e713cb466";

    @Test
    void query() throws IOException {
        TrainQueryRequest request = new TrainQueryRequest();
        request.setToken(token);
        request.setResourceCode(11);
        request.setPageNo(1);
        request.setPageSize(20);
//        String url = "http://39.106.226.73/manage/staff/train/query";
        String url = "http://localhost/manage/staff/train/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void add() throws IOException {
        TrainAddRequest request = new TrainAddRequest();
        request.setToken(token);
        request.setResourceCode(11);
        request.setName("测试");
        request.setTrainTime("2020-11-13 15:00:00");
        String url = "http://localhost/manage/staff/train/add";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void template() throws IOException {
        CommonRequest request = new CommonRequest();
        request.setToken(token);
        request.setResourceCode(11);
        String url = "http://localhost/manage/staff/train/template";
//        String url = "http://39.106.226.73/manage/staff/train/template";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse<CommonTemplateResponse> response =
                JsonTools.json2Object(responseStr, CommonResponse.class, CommonTemplateResponse.class);
        echo(response);
        byte[] b = Base64Utils.decodeFromString(response.getData().getTemplate());
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }

        OutputStream out = new FileOutputStream("C:\\Users\\霍佳进\\Desktop\\培训人员模板1.xlsx");
        out.write(b);
        out.flush();
        out.close();
    }

    @Test
    void importPapers() throws IOException {
        TrainImportRequest request = new TrainImportRequest();
        request.setToken(token);
        request.setResourceCode(11);
        request.setTrainId("eea0760bbb7841be908fc23c6422c6ab");
        //文件转base64
        FileInputStream fis = new FileInputStream("C:\\Users\\霍佳进\\Desktop\\培训人员模板1.xlsx");
        byte[] buffer = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        buffer = bos.toByteArray();

        request.setTrainFile(Base64.encodeBase64String(buffer));
        String url = "http://localhost/manage/staff/train/import";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    void peopleQuery() throws IOException {
        TrainPeopleQueryRequest request = new TrainPeopleQueryRequest();
        request.setToken(token);
//        request.setCampName("第一营业区");
        request.setResourceCode(21);
        request.setTrainId("7c33958e31ba490e8e3b77475b695dfd");
        String url = "http://localhost/manage/staff/train/people/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void peopleExport() throws IOException {
        TrainPeopleQueryRequest request = new TrainPeopleQueryRequest();
        request.setToken(token);
//        request.setCampName("第一营业区");
        request.setResourceCode(21);
        request.setTrainId("7c33958e31ba490e8e3b77475b695dfd");
//        String url = "http://39.106.226.73/manage/test/paper/push";
        String url = "http://localhost/manage/staff/train/people/export";
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

        OutputStream out = new FileOutputStream("C:\\Users\\霍佳进\\Desktop\\培训人员名单.xlsx");
        out.write(b);
        out.flush();
        out.close();

        echo(responseStr);
    }
}
