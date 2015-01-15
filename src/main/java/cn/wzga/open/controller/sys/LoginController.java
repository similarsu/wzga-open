package cn.wzga.open.controller.sys;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.cache.SettingCache;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.entity.sys.SysLoginLog;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.service.sys.SysLoginLogService;
import cn.wzga.open.service.sys.SysRoleService;
import cn.wzga.open.service.sys.SysUserService;
import cn.wzga.open.util.WebConstant;

import com.jit.attr.GenGACode;
import com.jit.exception.GACertParseException;

/**
 * <p>
 * description:登陆页控制层
 * </p>
 * 
 * @author chenjingchai
 * @version v1.0
 * @since 2014年11月4日
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "sysUserService")
    private SysUserService sysUserService;
    @Resource(name = "sysLoginLogService")
    private SysLoginLogService sysLoginLogService;
    @Resource(name = "sysDepartmentService")
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 登录页面
     * 
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {
        // 防止https下设置session， 会给cookie设置secure，跳转到http就不能取到session
        request.getSession(true);
        return "login";
    }

    /**
     * 登录
     * 
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "loginName", required = true) String loginName,
            @RequestParam(value = "password", required = true) String password) throws Exception {
        super.errorForward("login");
        SysUser sysUser = sysUserService.findByLoginNamePassword(loginName, password);
        if (sysUser == null) {
            throw new Exception("error.login.errorAccPwd");
        }
        SysDepartment sysDepartment = sysDepartmentService.findByCode(sysUser.getDeptCode());
        getRequest().getSession().setAttribute(Constant.SYS_USER_SESSION, sysUser);
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIdentifyCode(sysUser.getIdentifyCode());
        sysLoginLog.setName(sysUser.getChineseName());
        sysLoginLog.setLogDate(new Date());
        sysLoginLog.setIp(this.getClientIp());
        sysLoginLog.setStatus(WebConstant.LOGIN_NORMAL_IN);
        sysLoginLog.setCreator(sysUser);
        sysLoginLog.setCreateUnit(sysDepartment.getDeptName());
        sysLoginLog.setCreateUnitCode(sysDepartment.getDeptCode());
        sysLoginLogService.add(sysLoginLog);
        return "redirect:/main";
    }

    /**
     * pki登录页面
     * 
     * @return
     */
    @RequestMapping(value = "/pkilogin", method = RequestMethod.GET)
    public String toPkiLogin(HttpServletRequest request, ModelMap modelMap) {
        // 防止https下设置session， 会给cookie设置secure，跳转到http就不能取到session
        request.getSession(true);

        if (request.getScheme().toLowerCase().equals("http")) {
            StringBuilder url = new StringBuilder("https://");
            url.append(SettingCache.getServerDomain());
            url.append(":");
            url.append(SettingCache.getPkiPort());

            if (!StringUtil.isBlank(request.getContextPath())) {
                url.append(request.getContextPath());

            }

            url.append("/pkilogin");
            modelMap.put("loginURL", url.toString());

        }
        return "pki_login";
    }

    /**
     * pki登录
     * 
     * @return
     * @throws Exception
     * @author cl
     * @since 2015/12/31
     */
    @RequestMapping(value = "/pkilogin", method = RequestMethod.POST)
    public String pkiLogin(HttpServletRequest request) {

        String identifyCode = getIdentityNumberFromRequest(request);
        if (StringUtil.isBlank(identifyCode)) {
            Messages.setWarnMessage("warn.pki.needcerts");
            return "pki_login";

        }

        SysUser sysUser = null;
        try {
            sysUser = sysUserService.findByIdentifyCode(identifyCode);

        } catch (Exception e) {}

        if (sysUser == null) {
            return "redirect:/pkiregister";
        }

        getRequest().getSession().setAttribute(Constant.SYS_USER_SESSION, sysUser);
        SysDepartment sysDepartment = sysDepartmentService.findByCode(sysUser.getDeptCode());
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIdentifyCode(sysUser.getIdentifyCode());
        sysLoginLog.setName(sysUser.getChineseName());
        sysLoginLog.setLogDate(new Date());
        sysLoginLog.setIp(this.getClientIp());
        sysLoginLog.setStatus(WebConstant.LOGIN_PKI_IN);
        sysLoginLog.setCreator(sysUser);
        sysLoginLog.setCreateUnit(sysDepartment.getDeptName());
        sysLoginLog.setCreateUnitCode(sysDepartment.getDeptCode());
        sysLoginLogService.add(sysLoginLog);
        return "redirect:/main";
    }

    /**
     * pki注册页面
     * 
     * @return
     * @author cl
     * @since 2015/12/31
     */
    @RequestMapping(value = "/pkiregister", method = RequestMethod.GET)
    public String toPkiRegister(@ModelAttribute SysUser sysUser, ModelMap map) throws Exception {
        X509Certificate[] certs =
                (X509Certificate[]) getRequest().getAttribute(
                        "javax.servlet.request.X509Certificate");

        if (certs == null) {
            throw new RuntimeException("error.pki.register");
        }

        X509Certificate gacert = certs[0];
        String strDN = gacert.getSubjectDN().toString();
        String identifyCode = strDN.substring(strDN.indexOf(" ") + 1, strDN.indexOf(","));
        String chineseName = strDN.substring(3, strDN.indexOf(" "));

        // 获取机构代码
        GenGACode ga = new GenGACode();
        ga.setx509(certs[0]);
        ga.parserDN();
        String deptCode =
                ga.getgaxS() + ga.getgaxLCity() + ga.getgaxLCounty() + ga.getgaxOU78()
                        + ga.getgaxOU9a() + ga.getgaxOUbc();

        sysUser.setLoginName(identifyCode);
        sysUser.setIdentifyCode(identifyCode);
        sysUser.setDeptCode(deptCode);
        sysUser.setChineseName(chineseName);

        return "pki_register";
    }

    /**
     * pki注册
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pkiregister", method = RequestMethod.POST)
    public String pkiRegister(@ModelAttribute SysUser sysUser) throws Exception {
        /*
         * 查找角色根据名字 默认角色
         */
        SysRole criteria = new SysRole();
        criteria.setName(SettingCache.getRegRoleName());
        SysRole sysRole =
                sysRoleService.find(Where.getInstance(criteria).equal("name"), Join.getInstance()
                        .join("modifyMan"));

        if (null == sysRole) {
            throw new Exception(ERR_MSG_FIND);

        }
        // 设置默认角色
        Date now = new Date();
        Set<SysRole> roleList = new TreeSet<SysRole>();
        roleList.add(sysRole);

        sysUser.setRoleList(roleList);

        // 设置默认用户-超级管理员
        sysUser.setCreator(sysRole.getModifyMan());
        sysUser.setCreateDate(now);

        sysUser.setModifyMan(sysUser.getCreator());
        sysUser.setModifyDate(now);

        sysUser.setPassword(StringUtil.toRandom(6));

        SysDepartment sysDepartment = sysDepartmentService.findByCode(sysUser.getDeptCode());
        sysUser.setSysDepartment(sysDepartment);

        // 新增用户
        sysUserService.register(sysUser, this.getClientIp());

        SysUser sysUserTmp = sysUserService.findByIdentifyCode(sysUser.getIdentifyCode());

        getRequest().getSession().setAttribute(Constant.SYS_USER_SESSION, sysUserTmp);

        return "redirect:/main";
    }

    /**
     * 登出操作
     * 
     * @return login
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        SysUser sysUser = getSysUserSession();
        SysDepartment sysDepartment = sysDepartmentService.findByCode(sysUser.getDeptCode());
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIdentifyCode(sysUser.getIdentifyCode());
        sysLoginLog.setName(sysUser.getChineseName());
        sysLoginLog.setLogDate(new Date());
        sysLoginLog.setIp(this.getClientIp());
        sysLoginLog.setStatus(WebConstant.LOGIN_OUT);
        sysLoginLog.setCreator(sysUser);
        sysLoginLog.setCreateUnit(sysDepartment.getDeptName());
        sysLoginLog.setCreateUnitCode(sysDepartment.getDeptCode());
        sysLoginLogService.add(sysLoginLog);
        this.getRequest().getSession().invalidate();
        return "redirect:pkilogin";
    }

    private String getIdentityNumberFromRequest(HttpServletRequest request) {
        X509Certificate[] certs =
                (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

        if (certs == null) {
            return null;
        }

        // 获取机构代码
        GenGACode ga = new GenGACode();
        ga.setx509(certs[0]);
        try {
            ga.parserDN();
        } catch (GACertParseException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(null, e);
            }
            return null;

        }

        String strDN = certs[0].getSubjectDN().toString();
        return strDN.substring(strDN.indexOf(" ") + 1, strDN.indexOf(","));

    }

}
