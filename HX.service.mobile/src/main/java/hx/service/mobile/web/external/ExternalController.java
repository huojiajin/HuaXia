package hx.service.mobile.web.external;

import hx.base.core.dao.dict.message.ContentType;
import hx.base.core.dao.entity.message.MessageCustom;
import hx.base.core.dao.repo.jpa.message.MessageCustomRepo;
import hx.base.core.manage.common.CommonAbstract;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @name: ExternalController
 * @description: 对外功能Controller
 * @author: huojiajin
 * @time: 2021/3/19 11:12
 */
@RestController
@RequestMapping("/external")
public class ExternalController extends CommonAbstract {

    @Autowired
    private MessageCustomRepo customRepo;

    /**
     * @Name getImage
     * @Author HuoJiaJin
     * @Description 显示自定义消息中的图片
     * @Date 2021/3/19 11:31
     * @Param [id, response]
     * @Return void
     **/
    @GetMapping("/image/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) {
        Optional<MessageCustom> op = customRepo.findById(id);
        if (op.isPresent()) {
            MessageCustom custom = op.get();
            if (custom.getContentType() == ContentType.IMAGE) {
                //获取返回文件类型
                String[] split = new String(custom.getImage()).split(",");
                String[] split1 = split[0].split(";");
                String[] split2 = split1[0].split(":");
                response.setContentType(split2[1]);
                String base64Str = new String(custom.getImage());
                base64Str = base64Str.replace("+", "%2B");
                try {
                    byte[] decode = Base64.decodeBase64(split[1]);
                    ByteArrayOutputStream output = new ByteArrayOutputStream();//可以捕获内存缓冲区【生成的图片在缓冲区里面】的数据，将数据装换成字节数组 ,输出流的缓冲区的大小会随着数据的不断写入而自动增加 使用toByteArray() toString()获得生成字节数组的数据
                    output.write(decode);
                    ServletOutputStream out = response.getOutputStream();//servlet程序想servletOutputStream或PrintWriter对象中写入数据将被servlet引擎从response中获得
                    output.writeTo(out);//将byte数组输出流的全部内容写到指定的输出流参数中
                    output.flush();
                    output.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }
}
