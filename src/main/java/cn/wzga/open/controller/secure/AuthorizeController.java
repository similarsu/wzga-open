package cn.wzga.open.controller.secure;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wzga.core.util.Constant;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.dev.ApplicationService;
import cn.wzga.open.service.secure.OAuthService;
import cn.wzga.open.service.sys.SysUserService;
import cn.wzga.open.util.WebConstant;

@Controller
public class AuthorizeController extends BaseController {

    @Resource(name = "oAuthService")
    private OAuthService oAuthService;
    @Resource(name = "applicationService")
    private ApplicationService applicationService;

    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @RequestMapping("/authorize")
    public Object authorize(Model model, HttpServletRequest request) throws URISyntaxException,
            OAuthSystemException {

        try {
            // 构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

            // 检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(WebConstant.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response
                        .getResponseStatus()));
            }

            SysUser sysUser = getSysUserSession();
            // 如果用户没有登录，跳转到登陆页面
            if (sysUser == null) {// 登录失败时跳转到登陆页面
                if ((sysUser = login(request)) == null) {// 登录失败时跳转到登陆页面
                    model.addAttribute("client",
                            applicationService.findByAppId(oauthRequest.getClientId()));
                    return "oauth2login";
                }
            }

            String username = sysUser.getLoginName();
            // 生成授权码
            String authorizationCode = null;
            // responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                oAuthService.addAuthCode(authorizationCode, username);
            }

            // 进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            // 设置授权码
            builder.setCode(authorizationCode);
            // 得到到客户端重定向地址
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

            // 构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

            // 根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {

            // 出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                // 告诉客户端没有传入redirectUri直接报错
                return new ResponseEntity("OAuth callback url needs to be provided by client!!!",
                        HttpStatus.NOT_FOUND);
            }

            // 返回错误消息（如?error=）
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e)
                            .location(redirectUri).buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        }
    }

    private SysUser login(HttpServletRequest request) {
        if ("get".equalsIgnoreCase(request.getMethod())) {
            return null;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }
        SysUser sysUser = sysUserService.findByLoginNamePassword(username, password);

        if (sysUser != null) {
            request.getSession().setAttribute(Constant.SYS_USER_SESSION, sysUser);
            return sysUser;
        } else {
            request.setAttribute("error", "登录失败:");
            return null;
        }
    }
}
