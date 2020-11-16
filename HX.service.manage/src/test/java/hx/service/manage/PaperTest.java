package hx.service.manage;

import com.google.common.collect.Lists;
import hx.base.core.manage.model.CommonResponse;
import hx.base.core.manage.tools.JsonTools;
import hx.base.core.manage.tools.httpclient.HttpClientHelper;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonTemplateResponse;
import hx.service.manage.manage.model.test.papers.*;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * @name: PaperTest
 * @description: 试卷管理Test
 * @author: huojiajin
 * @time: 2020/6/22 10:16
 */
public class PaperTest extends ApplicationTests{

    private static final String token = "82f2dba491394eecac35415e713cb466";

    @Test
    void query() throws IOException {
        PapersQueryRequest request = new PapersQueryRequest();
        request.setToken(token);
        request.setResourceCode(31);
        request.setName("");
        request.setType(null);
        request.setPageNo(1);
        request.setPageSize(20);
//        String url = "http://123.56.154.176/manage/test/paper/query";
        String url = "http://localhost/manage/test/paper/query";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void add() throws IOException {
        PapersAddRequest request = new PapersAddRequest();
        request.setToken(token);
        request.setResourceCode(31);
        request.setName("测试试卷");
        request.setEndTime("2020-06-22 11:00:00");
        request.setAnswerTime(10);
        request.setType(1);
        String url = "http://localhost/manage/test/paper/add";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void template() throws IOException {
        CommonRequest request = new CommonRequest();
        request.setToken(token);
        request.setResourceCode(31);
//        String url = "http://localhost/manage/test/paper/template";
        String url = "http://123.56.154.176/manage/test/paper/template";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        CommonResponse<CommonTemplateResponse> response =
                JsonTools.json2Object(responseStr, CommonResponse.class, CommonTemplateResponse.class);
        byte[] b = Base64Utils.decodeFromString(response.getData().getTemplate());
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }

        OutputStream out = new FileOutputStream("C:\\Users\\霍佳进\\Desktop\\试卷模板1.xlsx");
        out.write(b);
        out.flush();
        out.close();
    }

    @Test
    void importPapers() throws IOException {
        PapersImportReqeust request = new PapersImportReqeust();
        request.setToken(token);
        request.setResourceCode(31);
        request.setPaperId("c147499ef7a0466883e155a55dd91dd9");
        //文件转base64
        FileInputStream fis = new FileInputStream("C:\\Users\\霍佳进\\Desktop\\试卷模板.xlsx");
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

        request.setPaperFile(Base64.encodeBase64String(buffer));
        String url = "http://localhost/manage/test/paper/import";
        String responseStr = HttpClientHelper.jsonPost(url, request.toJson());
        echo(responseStr);
    }

    @Test
    void view() throws IOException {
        PapersIdRequest request = new PapersIdRequest();
        request.setToken(token);
        request.setResourceCode(31);
        request.setId("c147499ef7a0466883e155a55dd91dd9");
        String url = "http://localhost/manage/test/paper/view";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void push() throws IOException {
        PapersPushRequest request = new PapersPushRequest();
        request.setToken(token);
        request.setResourceCode(31);
        request.setPaperId("bc1f6adfff3e4316b0a6ac7d087f3025");
        request.setCodeList(Lists.newArrayList("AS", "BC"));
//        String url = "http://123.56.154.176/manage/test/paper/push";
        String url = "http://localhost/manage/test/paper/push";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }

    @Test
    void campList() throws IOException {
        CommonRequest request = new CommonRequest();
        request.setToken(token);
        request.setResourceCode(11);
//        String url = "http://123.56.154.176/manage/test/paper/push";
        String url = "http://localhost/manage/test/paper/push/campList";
        String s = HttpClientHelper.jsonPost(url, request.toJson());
        echo(s);
    }
}
