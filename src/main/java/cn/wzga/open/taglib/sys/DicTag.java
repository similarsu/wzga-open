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

import cn.wzga.core.util.StringUtil;
import cn.wzga.open.cache.SysDicCache;
import cn.wzga.open.entity.sys.SysDic;
import cn.wzga.open.service.sys.SysDicService;

/**
 * <p>
 * 	Description:数据字典标签库
 * </p>
 * @author sutong
 * @version 1.0 2014-07-11
 */
public class DicTag extends SimpleTagSupport {
	private String type;
	private String dicType;//数据字典类型
	private String dicKey;//数据字典键

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		SysDicService sysDicService = (SysDicService) wac.getBean("sysDicService");

		if ("select".equals(type)) {
			if (dicKey!=null) {
				if(StringUtil.isBlank(dicKey)){
					out.print("");
				}else{
					out.print((SysDicCache.get(dicType, dicKey)==null?dicKey:SysDicCache.get(dicType, dicKey)));
				}
				
			} else {
				List<SysDic> sysDicList = SysDicCache.getFromList(dicType);
				String json = StringUtil.jsonSelect(sysDicList, "dicKey", "dicValue");
				out.print(json);
			}
		} else if ("parent".equals(type)) {
			List<SysDic> sysDicList = sysDicService.findByGroup();
			String json = StringUtil.jsonSelect(sysDicList, "dicType", "typeDesc");
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

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getDicKey() {
		return dicKey;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	
}
