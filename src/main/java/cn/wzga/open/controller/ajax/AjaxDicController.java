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
import cn.wzga.open.entity.sys.SysDic;
import cn.wzga.open.service.sys.SysDicService;
/**
 * <p>
 * 异步获取数据字典信息
 * </p>
 * @author sutong
 * 
 * @version 1.0 2014-07-30
 */
@Controller
@RequestMapping("/ajax/dic")
public class AjaxDicController extends BaseController{
	@Resource(name="sysDicService")
	private SysDicService sysDicService;
	
	@RequestMapping(value = "/dicType/isExists", method = RequestMethod.POST)
	public void dicTypeExists(HttpServletResponse response) throws Exception{
		PrintWriter out = super.outPrint(null, response);
		try {
			String dicType = super.getRequest().getParameter("dicType");
			if (StringUtil.isBlank(dicType)) {
				throw new RuntimeException();
			}

			Where where = Where.getInstance().equal("sysDic.dicType", dicType);
			SysDic sysDic = sysDicService.find(where);

			if (sysDic == null) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception ex) {
			out.print("false");
		}
	}
	
	@RequestMapping(value = "/dicKey/isExists", method = RequestMethod.POST)
	public void dicKeyExists(HttpServletResponse response) throws Exception{
		PrintWriter out = super.outPrint(null, response);
		try {
			String dicType=super.getRequest().getParameter("dicType");
			String dicKey = super.getRequest().getParameter("dicKey");
			if (StringUtil.isBlank(dicType)||StringUtil.isBlank(dicKey)) {
				throw new RuntimeException();
			}

			Where where = Where.getInstance().equal("sysDic.dicType", dicType).equal("sysDic.dicKey",dicKey);
			SysDic sysDic = sysDicService.find(where);

			if (sysDic == null) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (Exception ex) {
			out.print("false");
		}
	}
}
