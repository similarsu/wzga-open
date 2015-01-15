package cn.wzga.open.interceptor;

import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysUserService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Set;

/**
 * <p>
 * Description: 操作权限拦截器
 * </p>
 *
 * @author sutong
 * @version 1.0 2014-07-03
 */
public class OperationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request,
                             javax.servlet.http.HttpServletResponse response, java.lang.Object handler)
            throws java.lang.Exception {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Constant.SYS_USER_SESSION);

        String uri = request.getRequestURI();

        if (sysUser != null) {
            //获取所有被禁止的操作资源
            if (isMatchResource(uri, sysUser.getResourceList())) {
                return true;
            }

			/*
            如果是admin的角色则校验是否在角色创建人的资源中
            if is admin:
				find all resources in the role
				if match：
					return true
			 */
            SysRole adminRole = sysUserService.queryAdminRole(sysUser);
            if (null != adminRole) {
                if (this.isMatchResource(uri, sysUserService.queryCurrentUserResources(sysUser))) {
                    return true;
                }
            }

        }
        Messages.setErrorMessage("error.sys.notallowed");
        request.getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(request,
                response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }

    /**
     * <p>校验uri是否在资源内</p>
     *
     * @param uri
     * @param resources
     * @return
     * @author cl
     * @since 2014/12/17
     */
    private boolean isMatchResource(String uri, Set<SysResource> resources) {
        if (StringUtil.isEmpty(uri) || CollectionUtils.isEmpty(resources)) {
            return false;
        }

        for (SysResource sysResource : resources) {
            String url = sysResource.getUrl();
            if (!StringUtil.isBlank(url) && !"#".equals(url)) {
                //XXX 默认一个星号，如果匹配多个星号需修改
                int i = url.indexOf("*");
                if (i > -1) {
                    String urlSub1 = url.substring(0, i);
                    String urlSub2 = url.substring(i + 1);
                    if (uri.indexOf(urlSub1) > -1 && uri.indexOf(urlSub2) > -1) {
                        return true;
                    }
                } else {
                    if (uri.indexOf(sysResource.getUrl()) > -1)
                        return true;

                }
            }
        }

        return false;
    }
}
