package cn.wzga.open.controller.sys;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.AbstractBaseDeletableEntity;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysResourceService;
import cn.wzga.open.service.sys.SysRoleService;
import cn.wzga.open.service.sys.SysUserService;
import cn.wzga.open.util.WebConstant;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * description:角色管理控制层
 * </p>
 *
 * @author chenjingchai
 * @version v1.0
 * @since 2010年1月28日
 */
@Controller
@RequestMapping(value = "/sys/role")
public class SysRoleController extends BaseController {
    @Resource(name = "sysRoleService")
    private SysRoleService sysRoleService;
    @Resource(name = "sysResourceService")
    private SysResourceService sysResourceService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * <p>
     * description:列表
     * </p>
     *
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysRole
     * @return
     * @throws Exception
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysRole sysRole) throws Exception {

        sysRole.setModifyMan(this.getSysUserSession());
        sysRole.setState(AbstractBaseDeletableEntity.ENTITY_STATE_NORMAL);
        Where where = Where.getInstance(sysRole).equal("name").equal("modifyMan").equal("state");
        Join join = Join.getInstance().join("creator").join("modifyMan");
        Order order = Order.getInstance().desc("sysRole.modifyDate");
        Pager<SysRole> pager = new Pager<SysRole>(pageNo, pageSize, where, order, join);

        pager = sysRoleService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/role/role_list";
    }

    /**
     * <p>
     * description:添加页面
     * </p>
     *
     * @param modelMap
     * @param sysRole
     * @return
     * @author chenjingchai
     * @author cl
     * @since 2014/12/17
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(ModelMap modelMap, @ModelAttribute SysRole sysRole) {
        modelMap.put("sysResourceAllList", this.queryCurrentUserResources(this.getSysUserSession()));
        return "sys/role/role_add";
    }

    /**
     * <p>
     * description:添加数据
     * </p>
     *
     * @param sysRole
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute SysRole sysRole) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        String resIds = this.getRequest().getParameter("resIds");
        Set<SysResource> resList = StringUtil.ids2Set(SysResource.class, resIds);
        Date now = new Date();
        sysRole.setResourceList(resList);
        sysRole.setCreator(sysUser);
        sysRole.setModifyMan(sysUser);
        sysRole.setCreateDate(now);
        sysRole.setModifyDate(now);
        sysRole.setState(AbstractBaseDeletableEntity.ENTITY_STATE_NORMAL);
        sysRoleService.add(sysRole);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/role/list";
    }

    /**
     * <p>
     * description:修改页面
     * </p>
     *
     * @param modelMap
     * @param id
     * @return
     * @throws Exception
     * @author chenjingchai
     * @author cl
     * @since 2014/12/17
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(ModelMap modelMap, @PathVariable("id") Long id) throws Exception {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        modelMap.put("sysRole", sysRoleService.find(
                Where.getInstance(sysRole).equal("id"),
                Join.getInstance().join("resourceList")
        ));
        modelMap.put("sysResourceAllList", this.queryCurrentUserResources(this.getSysUserSession()));
        return "sys/role/role_update";
    }

    /**
     * <p>
     * description:修改数据
     * </p>
     *
     * @param sysRole
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute SysRole sysRole) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        String resIds = this.getRequest().getParameter("resIds");
        Set<SysResource> resList = StringUtil.ids2Set(SysResource.class, resIds);
        sysRole.setResourceList(resList);
        sysRole.setModifyMan(sysUser);
        sysRole.setModifyDate(new Date());
        sysRoleService.update(sysRole, "name", "remark", "modifyMan", "modifyDate", "resourceList");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/sys/role/list" + Constant.EXT;
    }

    /**
     * <p>
     * description:删除数据
     * </p>
     *
     * @param id
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        if (id == WebConstant.USER_ADMIN_ROLE) {
            Messages.setWarnMessage("warn.role.adminrole");

        } else {
            SysRole updateModel = new SysRole();
            updateModel.setId(id);
            updateModel.setState(AbstractBaseDeletableEntity.ENTITY_STATE_DELETED);
            sysRoleService.update(updateModel, "state");
            Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        }


        return "redirect:/sys/role/list" + Constant.EXT;

    }

    /**
     * <p>
     * description:菜单树形展现
     * </p>
     *
     * @param resListAll
     * @param resResults
     * @param parentId
     * @param level
     * @author chenjingchai
     * @since 2010年1月28日
     */
    private void res2ZTreeTable(Set<SysResource> resListAll, List<SysResource> resResults,
                                Long parentId, int level) {

        if (CollectionUtils.isEmpty(resListAll) || resResults == null) {
            return;

        }

        for (SysResource sysResource : resListAll) {
            if (sysResource.getResLevel() == level) {
                // 第一层父类菜单为空
                if (parentId == null) {
                    resResults.add(sysResource);
                    res2ZTreeTable(resListAll, resResults, sysResource.getId(), level + 1);
                } else if (sysResource.getParent().getId() == parentId) {
                    resResults.add(sysResource);
                    res2ZTreeTable(resListAll, resResults, sysResource.getId(), level + 1);
                }
            }
        }
    }

    private List<SysResource> queryCurrentUserResources(SysUser sysUserSession) {
        Set<SysResource> resourceList = sysUserService.queryCurrentUserResources(sysUserSession);
        List<SysResource> sysResourceList = new ArrayList<SysResource>();
        res2ZTreeTable(resourceList, sysResourceList, null, 2);
        return sysResourceList;
    }

}
