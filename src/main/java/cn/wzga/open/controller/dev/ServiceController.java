package cn.wzga.open.controller.dev;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.PropertyUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.dev.Service;
import cn.wzga.open.service.dev.ServiceService;

@Controller
@RequestMapping(value = "/dev/service")
public class ServiceController extends BaseController {
    @Resource(name = "serviceService")
    private ServiceService serviceService;

    /**
     * 添加页面
     * 
     * @param service
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute Service service) {
        return "/dev/service/service_add";
    }

    /**
     * 添加数据
     * 
     * @param service
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Service service) {
        serviceService.add(service);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);
        return "redirect:/dev/service/list";
    }

    /**
     * 修改页面
     * 
     * @param id
     * @param service
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, @ModelAttribute Service service)
            throws Exception {
        Service serviceTemp = serviceService.load(id);
        PropertyUtil.copyProperties(service, serviceTemp);
        return "dev/service/service_update";
    }

    /**
     * 修改数据
     * 
     * @param service
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Service service) {
        serviceService.update(service, "name", "serId", "serSecret", "creator", "createDate",
                "modifyMan", "modifyDate", "state", "isAdmin");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/dev/service/list";
    }

    /**
     * 列表
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param service
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute Service service) throws Exception {

        Where where = Where.getInstance(service);
        Pager<Service> pager = new Pager<Service>(pageNo, pageSize, where);

        pager = serviceService.findPage(pager);

        modelMap.put("pager", pager);

        return "/dev/service/service_list";
    }

    /**
     * 删除数据
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        serviceService.delete(id);

        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/dev/service/list";

    }
}
