package hx.service.manage.manage;

import hx.service.manage.dao.dict.ErrorType;
import hx.service.manage.dao.entity.acl.RoleResource;
import hx.service.manage.dao.entity.acl.User;
import hx.service.manage.dao.repo.jpa.acl.RoleResourceRepo;
import hx.service.manage.manage.model.CommonRequest;
import hx.service.manage.manage.model.CommonResponse;
import hx.service.manage.manage.tools.CollectionTools;
import hx.service.manage.manage.tools.JsonTools;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: AclHandlerInterceptor
 * @description: 自定义拦截器
 * @author: huojiajin
 * @time: 2020/5/27 16:25
 */
@Service
public class AclHandlerInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private RoleResourceRepo roleResourceRepo;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        try {
            String requestURI = request.getRequestURI();
            logger.info("requestURI:{}", requestURI);
            String[] urlSplitArr = requestURI.substring(1).split("/");
            if (urlSplitArr[0].equals("manage")){//电脑端
                if(!urlSplitArr[1].equals("login")){//除登陆操作之外
                    String requestData = getOpenApiRequestData(request);
                    logger.info("request:{}", requestData);
                    CommonRequest commonRequest = JsonTools.json2Object(requestData, CommonRequest.class);
                    String userKey = MyMecachedPrefix.loginTokenPrefix + commonRequest.getToken();
                    Object userObject = memcachedClient.get(userKey);
                    if (userObject == null){
                        return errorResponse(response, ErrorType.NOLOGIN);
                    }
                    String userJson = (String)userObject;
                    User user = JsonTools.json2Object(userJson, User.class);
                    //校验是否有权限访问
                    String resourceKey = MyMecachedPrefix.loginResourcePrefix + user.getId();
                    Object resourceListObject = memcachedClient.get(resourceKey);
                    int resourceCode = commonRequest.getResourceCode();
                    if (resourceListObject == null){
                        List<Integer> resourceCodeList = getResourceCode(user);
                        if (CollectionTools.isEmpty(resourceCodeList)){
                            return errorResponse(response, ErrorType.NORESOURCE);
                        }
                        memcachedClient.set(MyMecachedPrefix.loginResourcePrefix + user.getId(), 10 * 60, resourceCodeList);
                        boolean contains = resourceCodeList.contains(resourceCode);
                        if (!contains) {
                            return errorResponse(response, ErrorType.NORESOURCE);
                        }
                    }else {
                        List<Integer> resourceCodeList = (List<Integer>) resourceListObject;
                        boolean contains = resourceCodeList.contains(resourceCode);
                        if (!contains) {
                            List<Integer> resourceCodeListNew = getResourceCode(user);
                            if (CollectionTools.isEmpty(resourceCodeList) || !resourceCodeListNew.contains(resourceCode)){
                                return errorResponse(response, ErrorType.NORESOURCE);
                            }
                            memcachedClient.set(MyMecachedPrefix.loginResourcePrefix + user.getId(), 10 * 60, resourceCodeListNew);
                        }else{
                            memcachedClient.touch(resourceKey, 30 * 60);
                        }
                    }
                    memcachedClient.touch(userKey, 30 * 60);
                }
            }else if (urlSplitArr[0].equals("mobile")){//移动端

            }
        } catch (Exception e) {
            logger.error("", e);
            return errorResponse(response, ErrorType.NOLOGIN);
        }
        return true;
    }

    public boolean errorResponse(HttpServletResponse response, ErrorType errorType) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setError(errorType);
        returnJson(response, commonResponse.toJson());
        return false;
    }

    public List<Integer> getResourceCode(User user) {
        List<RoleResource> roleResourceList = roleResourceRepo.listByRoleId(user.getId());
        return roleResourceList.stream().map(r -> r.getResourceType().getCode())
                .collect(Collectors.toList());
    }


    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private String getOpenApiRequestData(HttpServletRequest request){
        try {
            int contentLength = request.getContentLength();
            if (contentLength < 0) {
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength;) {

                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }

            String charEncoding = request.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            return new String(buffer, charEncoding);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
