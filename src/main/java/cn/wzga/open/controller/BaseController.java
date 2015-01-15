package cn.wzga.open.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import cn.wzga.core.editor.CustomDateEditor;
import cn.wzga.core.editor.CustomIntegerEditor;
import cn.wzga.core.editor.CustomLongEditor;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysUser;

/**
 * <p>
 * Description: 控制层基类
 * </p>
 *
 * @author sutong
 * @version 1.0 2014-08-22
 */
public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
        binder.registerCustomEditor(Integer.class, new CustomIntegerEditor(true));
        binder.registerCustomEditor(Long.class, new CustomLongEditor(true));
    }

    protected final static String ERROR = "/error_page.htm";

    protected final static String ADD_SUCCESS_MSG = "success.base.add";
    protected final static String UPDATE_SUCCESS_MSG = "success.base.update";
    protected final static String DELETE_SUCCESS_MSG = "success.base.delete";
    protected final static String AUDIT_SUCCESS_MSG = "success.base.audit";
    protected final static String NO_AUDIT_SUCCESS_MSG = "success.base.noaudit";
    protected final static String ERR_MSG_ADD = "error.base.add";
    protected final static String ERR_MSG_UPDATE = "error.base.update";
    protected final static String ERR_MSG_FIND = "error.base.find";
    protected final static String SAVE_SUCCESS_MSG = "success.base.save";
    protected final static String ERR_DELETE_MSG = "error.base.delete";



    protected String getCtx() {
        return this.getRequest().getContextPath();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 用于读取国际化配置文件
     *
     * @return
     */
    protected RequestContext getRequestContext() {
        return new RequestContext(getRequest());
    }

    protected void errorForward(String uri) {
        this.getRequest().setAttribute("errorForward", uri);
    }

    /**
     * 获取用户session
     *
     * @return
     */
    protected SysUser getSysUserSession() {
        return (SysUser) getRequest().getSession().getAttribute(Constant.SYS_USER_SESSION);
    }

    /**
     * 获取客户端IP
     *
     * @return String
     */
    protected String getClientIp() {
        String ip = this.getRequest().getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getRemoteAddr();
        }
        return ip;
    }

    /**
     * 在页面上输出HTML内容
     *
     * @param content
     * @param response
     * @throws IOException
     */
    protected PrintWriter outPrint(String content, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        if (!StringUtil.isBlank(content))
            out.print(content);

        return out;
    }

    /**
     * 在页面上输出JSON内容
     *
     * @param content
     * @param response
     * @throws IOException
     */
    protected PrintWriter outPrintJSON(String content, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        if (!StringUtil.isBlank(content))
            out.print(content);

        return out;
    }

    /**
     * 在页面上输出图片
     *
     * @param content
     * @param response
     * @return
     * @throws IOException
     */
    protected OutputStream outPrintBase64Image(String content, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        OutputStream os = response.getOutputStream();
        if (!StringUtil.isBlank(content)) {
            Base64 base64 = new Base64();
            byte[] b = base64.decode(content);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            os.write(b);
        }
        return os;
    }

}
