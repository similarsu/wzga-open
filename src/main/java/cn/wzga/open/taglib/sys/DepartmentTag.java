package cn.wzga.open.taglib.sys;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.service.sys.SysDepartmentService;

/**
 * <p>
 * Description:单位代码数据标签库
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-07-11
 * 
 */
public class DepartmentTag extends SimpleTagSupport {
    private String deptCode;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();
        ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
        WebApplicationContext wac =
                WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        SysDepartmentService sysDepartmentService =
                (SysDepartmentService) wac.getBean("sysDepartmentService");
        if (StringUtil.isBlank(deptCode)) {
            /*
             * List<SysDepartment> sysDepartmentList=sysDepartmentService.findAll(null, null);
             * String json=StringUtil.jsonSuggestion(sysDepartmentList, "deptCode",
             * "deptName","deptCode","deptName","deptSpell"); out.print(json);
             */
            out.print("");
        } else {
            SysDepartment sysDepartment =
                    sysDepartmentService.find(Where.getInstance().equal("deptCode", deptCode));
            if (sysDepartment != null) {
                out.print(sysDepartment.getDeptName());
            } else {
                out.print(deptCode);
            }

        }

        super.doTag();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

}
