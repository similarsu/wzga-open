package cn.wzga.open.controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysUserService;

/**
 * 
 * <p>
 * description:异步获取用户信息
 * </p>
 * 
 * @author chenjingchai
 * @since 2010年1月28日
 * @version v1.0
 */
@Controller
@RequestMapping("/ajax/user")
public class AjaxUserController extends BaseController {
    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @RequestMapping(value = "/loginName/isExists", method = RequestMethod.POST)
    public void loginNameExists(HttpServletResponse response) throws IOException {
        response.setContentType("text/html);charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        String loginName = super.getRequest().getParameter("loginName");
        SysUser sysUser = null;
        if (!StringUtil.isBlank(loginName)) {
            Where where = Where.getInstance().equal("sysUser.loginName", loginName);
            sysUser = sysUserService.find(where);
        }

        if (sysUser == null) {
            out.print("true");
        } else {
            out.print("false");
        }
    }

    @RequestMapping(value = "/policeCode/isExists", method = RequestMethod.POST)
    public void policeCodeExists(HttpServletResponse response) throws IOException {
        response.setContentType("text/html);charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        String policeCode = super.getRequest().getParameter("policeCode");
        SysUser sysUser = null;
        if (!StringUtil.isBlank(policeCode)) {
            Where where = Where.getInstance().equal("sysUser.policeCode", policeCode);
            sysUser = sysUserService.find(where);
        }

        if (sysUser == null) {
            out.print("true");
        } else {
            out.print("false");
        }
    }

    @RequestMapping(value = "/identifyCode/isExists", method = RequestMethod.POST)
    public void identifyCodeExists(HttpServletResponse response) throws IOException {
        response.setContentType("text/html);charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        String identifyCode = super.getRequest().getParameter("identifyCode");
        SysUser sysUser = null;
        if (!StringUtil.isBlank(identifyCode)) {
            Where where = Where.getInstance().equal("sysUser.identifyCode", identifyCode);
            sysUser = sysUserService.find(where);
        }

        if (sysUser == null) {
            out.print("true");
        } else {
            out.print("false");
        }
    }
}
