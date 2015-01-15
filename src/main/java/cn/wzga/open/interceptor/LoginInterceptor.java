package cn.wzga.open.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wzga.core.util.Constant;
import cn.wzga.open.entity.sys.SysUser;

/**
 * <p>
 * Description:登录拦截器
 * </p>
 * 
 * @author sutong
 * @author cl
 * @version 1.0 2014-07-02
 * @since 2014/12/26
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        try {
            SysUser sysUser =
                    (SysUser) request.getSession().getAttribute(Constant.SYS_USER_SESSION);

            if (sysUser == null) {
                request.getRequestDispatcher("/WEB-INF/views/session_timeout.jsp").forward(request,
                        response);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

}
