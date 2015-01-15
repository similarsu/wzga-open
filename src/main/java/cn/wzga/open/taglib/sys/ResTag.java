package cn.wzga.open.taglib.sys;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.service.sys.SysResourceService;

/**
 * <p>
 * 	Description：资源标签库
 * </p>
 * @author sutong
 * @version 1.0 2014-06-27
 *
 */
public class ResTag extends SimpleTagSupport {
	private String type;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();

		ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		SysResourceService sysResourceService=(SysResourceService) wac.getBean("sysResourceService");

		if ("selectTree".equals(type)) {
			List<SysResource> resList = sysResourceService.findAll(Where.getInstance().equal("sysResource.state", 1).lessThan("sysResource.resLevel",3),
					Order.getInstance().asc("sysResource.resLevel").desc("parent.sort").asc("sysResource.sort"),
					Join.getInstance().join("parent"));
			String json = StringUtil.jsonSelectTree(resList, "id", "name", "parent.id");
			out.print(json);
		} else if ("select".equals(type)) {
			List<SysResource> resList = sysResourceService.findAll(Where.getInstance().equal("sysResource.resLevel",1),Order.getInstance().asc("sysResource.sort"));
			String json = StringUtil.jsonSelect(resList, "id", "name");
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
