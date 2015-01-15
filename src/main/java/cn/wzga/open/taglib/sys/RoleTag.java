package cn.wzga.open.taglib.sys;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysRoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * Description:角色标签库
 * </p>
 *
 * @author sutong
 * @version 1.0 2014-06-25
 */
public class RoleTag extends SimpleTagSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleTag.class);

    private String type;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();

        ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);
        SysRoleService sysRoleService = (SysRoleService) wac.getBean("sysRoleService");

        // 查找用户创建的角色
        SysUser sysUser = (SysUser) getJspContext()
                .getAttribute(Constant.SYS_USER_SESSION, PageContext.SESSION_SCOPE);
        SysRole queryCriteria = new SysRole();
        queryCriteria.setModifyMan(sysUser);

        List<SysRole> roleList = null;
        try {
            roleList = sysRoleService.findAll(
                    Where.getInstance(queryCriteria).equal("modifyMan"),
                    null);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("find role", e);
            }
        }

        if ("selectTree".equals(type)) {
            String json = StringUtil.jsonSelectTree(roleList, "id", "name", null);
            out.print(json);
        } else if ("select".equals(type)) {
            String json = StringUtil.jsonSelect(roleList, "id", "name");
            out.print(json);
        }

        super.doTag();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
