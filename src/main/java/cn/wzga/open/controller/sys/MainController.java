package cn.wzga.open.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.util.JQueryMenuMaker;

/**
 * <p>
 * Description:主页控制层
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-06-20
 */
@Controller
public class MainController extends BaseController {

    @RequestMapping("main")
    public String mainPage(ModelMap modelMap) {
        return "main";
    }

    @RequestMapping("default")
    public String defaultPage() {
        return "default";
    }

    @RequestMapping("left")
    public String leftPage(ModelMap modelMap) {
        SysUser sysUser = getSysUserSession();
        String menu = JQueryMenuMaker.res2TreeAccordionMenu(sysUser.getResourceList());
        modelMap.put("menu", menu);
        return "left";
    }
}
