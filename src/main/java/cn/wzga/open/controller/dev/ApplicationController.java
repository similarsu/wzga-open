package cn.wzga.open.controller.dev;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.PropertyUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.dev.Application;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.dev.ApplicationService;

@Controller
@RequestMapping(value = "/dev/application")
public class ApplicationController extends BaseController {
    @Resource(name = "applicationService")
    private ApplicationService applicationService;

    /**
     * 添加页面
     * 
     * @param application
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute Application application) {
        return "dev/application/application_add";
    }

    /**
     * 添加数据
     * 
     * @param application
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Application application) {
        Date now = new Date();
        SysUser sysUser = this.getSysUserSession();
        application.setCreator(sysUser);
        application.setModifyMan(sysUser);
        application.setCreateDate(now);
        application.setModifyDate(now);
        application.setAppId(UUID.randomUUID().toString());
        application.setAppSecret(UUID.randomUUID().toString());
        applicationService.add(application);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);
        return "redirect:/dev/application/list";
    }

    /**
     * 修改页面
     * 
     * @param id
     * @param application
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, @ModelAttribute Application application)
            throws Exception {
        Application applicationTemp = applicationService.load(id);
        PropertyUtil.copyProperties(application, applicationTemp);
        return "dev/application/application_update";
    }

    /**
     * 修改数据
     * 
     * @param application
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Application application) {
        applicationService.update(application, "appName", "appId", "appSecret", "creator",
                "createDate", "modifyMan", "modifyDate", "state");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/dev/application/list";
    }

    /**
     * 列表
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param application
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute Application application) throws Exception {

        Where where = Where.getInstance(application);
        Order order = Order.getInstance().desc("sysUser.modifyDate");
        Join join = Join.getInstance().join("sysDepartment").join("creator").join("modifyMan");
        Pager<Application> pager = new Pager<Application>(pageNo, pageSize, where, order, join);

        pager = applicationService.findPage(pager);

        modelMap.put("pager", pager);

        return "/dev/application/application_list";
    }

    /**
     * 删除数据
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        applicationService.delete(id);

        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/dev/application/list";

    }
}
