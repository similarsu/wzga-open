package cn.wzga.open.controller.sys;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.service.sys.SysUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * description:用户管理控制层
 * </p>
 *
 * @author chenjingchai
 * @version v1.0
 * @since 2010年1月28日
 */
@Controller
@RequestMapping(value = "/sys/user")
public class SysUserController extends BaseController {
    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @Resource(name = "sysDepartmentService")
    private SysDepartmentService sysDepartmentService;

    /**
     * <p>
     * description:列表
     * </p>
     *
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysUser
     * @return
     * @throws Exception
     * @author chenjingchai
     * @author cl
     * @since 2015/12/31
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysUser sysUser) throws Exception {

        sysUser.setDeptCode(this.getSysUserSession().getDeptCode());
        Where where =
                Where.getInstance(sysUser).equal("chineseName").equal("identifyCode")
                        .equal("policeCode").prefixLike("deptCode");
        Order order = Order.getInstance().desc("sysUser.modifyDate");
        Join join = Join.getInstance().join("sysDepartment").join("creator").join("modifyMan");
        Pager<SysUser> pager = new Pager<SysUser>(pageNo, pageSize, where, order, join);

        pager = sysUserService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/user/user_list";
    }

    /**
     * <p>
     * description:添加页面
     * </p>
     *
     * @param sysUser
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute SysUser sysUser) {
        return "sys/user/user_add";
    }

    /**
     * <p>
     * description:添加用户
     * </p>
     *
     * @param sysUser
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute SysUser sysUser) {
        SysUser sysUserTmp = this.getSysUserSession();
        if (sysUserTmp == null) {
            throw new RuntimeException("error.sys.interior");
        }
        String roleIds = getRequest().getParameter("roleIds");
        String deptCode = getRequest().getParameter("deptCode");
        Date now = new Date();
        sysUser.setCreator(sysUserTmp);
        sysUser.setModifyMan(sysUserTmp);
        sysUser.setCreateDate(now);
        sysUser.setModifyDate(now);
        Set<SysRole> roleList = StringUtil.ids2Set(SysRole.class, roleIds);
        sysUser.setRoleList(roleList);
        SysDepartment sysDepartment = sysDepartmentService.findByCode(deptCode);
        sysUser.setSysDepartment(sysDepartment);
        sysUser.setDeptCode(deptCode);
        sysUserService.add(sysUser);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/user/list";
    }

    /**
     * <p>
     * description:修改页面
     * </p>
     *
     * @param id
     * @return
     * @throws Exception
     * @author chenjingchai
     * @author cl
     * @since 2015/12/31
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, ModelMap modelMap)
            throws Exception {
        SysUser sysUser =
                sysUserService.load(id, Join.getInstance().join("roleList").join("sysDepartment"));
        String roleIds = StringUtil.set2Ids(sysUser.getRoleList());
        String deptCode = StringUtil.obj2Str(sysUser.getSysDepartment(), "deptCode");
        sysUser.setRoleIds(roleIds);
        sysUser.setDeptCode(deptCode);
        modelMap.put("sysUser", sysUser);
        return "sys/user/user_update";
    }

    /**
     * <p>
     * description:修改数据，密码若为空，则不修改密码
     * </p>
     *
     * @param sysUser
     * @return
     * @author chenjingchai
     * @since 2010年1月28日
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute SysUser sysUser) {

        String roleIds = getRequest().getParameter("roleIds");
        String deptCode = getRequest().getParameter("deptCode");
        sysUser.setModifyMan(this.getSysUserSession());
        sysUser.setModifyDate(new Date());
        Set<SysRole> roleList = StringUtil.ids2Set(SysRole.class, roleIds);
        sysUser.setRoleList(roleList);
        SysDepartment sysDepartment = sysDepartmentService.findByCode(deptCode);
        sysUser.setSysDepartment(sysDepartment);
        sysUser.setDeptCode(deptCode);
        if (!StringUtil.isBlank(sysUser.getPassword())) {
            sysUserService.update(sysUser, "loginName", "password", "policeCode", "chineseName",
                    "identifyCode", "phoneNumber", "email", "modifyMan", "modifyDate",
                    "sysDepartment", "roleList");
        } else {
            sysUserService.update(sysUser, "loginName", "policeCode", "chineseName",
                    "identifyCode", "phoneNumber", "email", "modifyMan", "modifyDate",
                    "sysDepartment", "roleList");
        }
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/sys/user/list";
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
        sysUserService.delete(id);

        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/sys/user/list";

    }
}
