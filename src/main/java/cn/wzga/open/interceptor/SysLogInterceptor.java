package cn.wzga.open.interceptor;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.entity.sys.SysLog;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.service.sys.SysLogService;
import cn.wzga.open.util.Configuration;

/**
 * <p>
 * Description: 系统日志拦截器
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-07-02
 */
public class SysLogInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SysLogInterceptor.class);

    @Resource(name = "sysLogService")
    private SysLogService sysLogService;
    @Resource(name = "sysDepartmentService")
    private SysDepartmentService sysDepartmentService;

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response, java.lang.Object handler)
            throws java.lang.Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        try {
            String uri = request.getServletPath();

            if (!Messages.hasErrors() && "POST".equalsIgnoreCase(request.getMethod())) {
                Configuration config = Configuration.getInstance();

                String operationType = null;
                String content = null;
                String id = null;

                Pattern pattern = Pattern.compile("/[0-9]{1,}");
                Matcher matcher = pattern.matcher(uri);
                if (matcher.find()) {
                    id = "id:" + matcher.group().substring(1);
                }

                uri = uri.replaceAll("/[0-9]{1,}", "/ID");

                String property = config.getValue(uri);
                if (!StringUtil.isBlank(property)) {
                    SysUser sysUser =
                            (SysUser) request.getSession().getAttribute(Constant.SYS_USER_SESSION);
                    if (sysUser == null) {
                        return;
                    }

                    String[] properties = property.split(",");
                    int i = 0;
                    for (String field : properties) {
                        if (i++ == properties.length) {
                            break;
                        }
                        if (i == 1) {
                            operationType = field;
                        } else if (i == 2) {
                            if (!StringUtil.isBlank(field))
                                content = field + ">>>" + (id == null ? "" : id + ",");
                        } else if (i >= 3) {
                            if (!StringUtil.isBlank(field)
                                    && !StringUtil.isBlank(request.getParameter(field)))
                                content += field + ":" + request.getParameter(field) + ",";
                        }
                    }
                    SysDepartment sysDepartment =
                            sysDepartmentService.findByCode(sysUser.getDeptCode());
                    SysLog sysLog = new SysLog();
                    sysLog.setOperateDate(new Date());
                    sysLog.setName(sysUser.getChineseName());
                    sysLog.setIdentifyCode(sysUser.getIdentifyCode());
                    sysLog.setOperateModule(operationType);
                    sysLog.setOperateContent(content.substring(0, content.length() - 1));
                    sysLog.setIp(request.getRemoteAddr());
                    sysLog.setCreator(sysUser);
                    sysLog.setCreateUnit(sysDepartment.getDeptName());
                    sysLog.setCreateUnitCode(sysDepartment.getDeptCode());
                    sysLogService.add(sysLog);
                }
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(null, e);

            }
        }
    }
}
