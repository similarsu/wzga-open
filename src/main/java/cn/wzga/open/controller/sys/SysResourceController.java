package cn.wzga.open.controller.sys;

import java.util.Date;

import javax.annotation.Resource;

import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.AbstractBaseDeletableEntity;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysResourceService;

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
import cn.wzga.core.util.StringUtil;

/**
 * <p>
 * description:资源管理控制层
 * </p>
 * 
 * @author chenjingchai
 * @since 2014年10月31日
 * @version v1.0
 */
@Controller
@RequestMapping(value = "/sys/res")
public class SysResourceController extends BaseController {
    @Resource(name = "sysResourceService")
    private SysResourceService sysResourceService;

    /**
     * <p>
     * description:列表
     * </p>
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysResource
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysResource sysResource) throws Exception {

        sysResource.setState(AbstractBaseDeletableEntity.ENTITY_STATE_NORMAL);
        Where where = Where.getInstance(sysResource).equal("name")
                .equal("url")
                .equal("state");
        Order order =
                Order.getInstance().asc("sysResource.resLevel").asc("parent.sort")
                        .asc("sysResource.sort");
        Join join = Join.getInstance().join("parent").join("creator").join("modifyMan");
        Pager<SysResource> pager = new Pager<SysResource>(pageNo, pageSize, where, order, join);

        pager = sysResourceService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/res/res_list";
    }

    /**
     * <p>
     * description:添加页面
     * </p>
     * 
     * @param sysResource
     * @return
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute SysResource sysResource) {
        return "sys/res/res_add";
    }

    /**
     * <p>
     * description:添加数据
     * </p>
     * 
     * @param sysResource
     * @return
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute SysResource sysResource) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        String parentId = getRequest().getParameter("parentId");
        Date today = new Date();
        SysResource parent = StringUtil.id2Object(SysResource.class, parentId);
        sysResource.setParent(parent);
        sysResource.setCreator(sysUser);
        sysResource.setModifyMan(sysUser);
        sysResource.setCreateDate(today);
        sysResource.setModifyDate(today);
        sysResource.setState(AbstractBaseDeletableEntity.ENTITY_STATE_NORMAL);
        sysResourceService.add(sysResource);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/res/list";
    }

    /**
     * <p>
     * description:修改页面
     * </p>
     * 
     * @param id
     * @param sysResource
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, @ModelAttribute SysResource sysResource)
            throws Exception {
        SysResource sysResourceTemp =
                sysResourceService.load(id, Join.getInstance().join("parent"));
        PropertyUtil.copyProperties(sysResource, sysResourceTemp);
        String parentId = StringUtil.obj2Id(sysResource.getParent());
        sysResource.setParentId(parentId);
        return "sys/res/res_update";
    }

    /**
     * <p>
     * description:修改数据
     * </p>
     * 
     * @param sysResource
     * @return
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute SysResource sysResource) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        String parentId = getRequest().getParameter("parentId");
        SysResource parent = StringUtil.id2Object(SysResource.class, parentId);
        sysResource.setParent(parent);
        sysResource.setModifyMan(sysUser);
        sysResource.setModifyDate(new Date());
        sysResourceService.update(sysResource, "name", "url", "type", "resLevel", "sort", "parent",
                "icon", "iconOpen", "iconClose", "modifyMan", "modifyDate");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/sys/res/list";
    }

    /**
     * <p>
     * description:删除数据
     * </p>
     * 
     * @param id
     * @return
     * 
     * @author chenjingchai
     * @since 2014年10月31日
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        SysResource sysResource = new SysResource();
        sysResource.setId(id);
        sysResource.setState(AbstractBaseDeletableEntity.ENTITY_STATE_DELETED);

        sysResourceService.update(sysResource, "state");

        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/sys/res/list";

    }

}
