package cc.mrbird.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MrBird
 */
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * RedirectStrategy是Spring提供的一个客户端跳转的工具类。使用它可以支持“/index.jsp”这种地址，同时可以保证服务器跳转和客户端跳转的路径一致。
     * 加入我们的项目名为my ，项目访问地址为http://localhost:8080/my
     * 现在要使用客户端跳转到 "/login.jsp" 如果是response.sendRedirect 会直接跳转到http://localhost:8080/login.jsp
     * 而使用redirectStrategy.sendRedirect则会跳转到http://localhost:8080/my/login.jsp
     * 在redirectStrategy中，'/'代表的是项目根目录而不是服务器根目录。
     *
     * @author 悟空
     * @description //TODO
     * @date 21:47 2021/4/17
     * @param null
     * @return null
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
                redirectStrategy.sendRedirect(request, response, "/login.html");
        }
        return "访问的资源需要身份认证！";
    }
}
