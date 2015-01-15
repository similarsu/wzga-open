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
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.service.sys.SysResourceService;
/**
 * <p>
 * 异步获取资源信息
 * </p>
 * @author sutong
 * 
 * @version 1.0 2014-07-29
 */
@Controller
@RequestMapping("/ajax/res")
public class AjaxResController extends BaseController{
	@Resource(name="sysResourceService")
	private SysResourceService sysResourceService;
	
	@RequestMapping(value = "/name/isExists", method = RequestMethod.POST)
	public void nameExists(HttpServletResponse response) throws Exception{
		PrintWriter out = super.outPrint(null, response);
		try {
			String name = super.getRequest().getParameter("name");
			if (StringUtil.isBlank(name)) {
				throw new RuntimeException();
			}

			Where where = Where.getInstance().equal("sysResource.name", name);
			SysResource sysResource = sysResourceService.find(where);

			if (sysResource == null) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception ex) {
			out.print("false");
		}
	}
	
	@RequestMapping(value = "/url/isExists", method = RequestMethod.POST)
	public void urlExists(HttpServletResponse response) throws Exception{
		PrintWriter out = super.outPrint(null, response);
		try {
			String url = super.getRequest().getParameter("url");
			if (StringUtil.isBlank(url)) {
				throw new RuntimeException();
			}

			Where where = Where.getInstance().equal("sysResource.url", url);
			SysResource sysResource = sysResourceService.find(where);

			if (sysResource == null) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception ex) {
			out.print("false");
		}
	}
}
