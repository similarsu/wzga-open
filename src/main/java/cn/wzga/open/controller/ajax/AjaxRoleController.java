package cn.wzga.open.controller.ajax;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





import org.springframework.web.bind.annotation.RequestMethod;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.service.sys.SysRoleService;
/**
 * <p>
 * 异步获取角色信息
 * </p>
 * @author sutong
 * 
 * @version 1.0 2014-07-29
 */
@Controller
@RequestMapping("/ajax/role")
public class AjaxRoleController extends BaseController{
	@Resource(name="sysRoleService")
	private SysRoleService sysRoleService;
	
	@RequestMapping(value = "/name/isExists", method = RequestMethod.POST)
	public void loginNameExists(HttpServletResponse response) throws Exception{
		PrintWriter out = super.outPrint(null, response);
		try {
			String name = super.getRequest().getParameter("name");
			if (StringUtil.isBlank(name)) {
				throw new RuntimeException();
			}

			Where where = Where.getInstance().equal("sysRole.name", name);
			SysRole sysRole = sysRoleService.find(where);

			if (sysRole == null) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception ex) {
			out.print("false");
		}
	}
}
