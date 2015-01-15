package cn.wzga.open.controller.sys;

import java.util.Date;

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
import cn.wzga.open.entity.sys.SysDic;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDicService;

/**
 * <p>
 * Description:数据字典管理控制层
 * </p>
 * 
 * @author sutong
 * @version 1.0 2014-07-11
 */
@Controller
@RequestMapping(value = "/sys/dic")
public class SysDicController extends BaseController {
    @Resource(name = "sysDicService")
    private SysDicService sysDicService;

    /**
     * <p>
     * description:列表
     * </p>
     * 
     * @param pageNo
     * @param pageSize
     * @param modelMap
     * @param sysDic
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysDic sysDic) throws Exception {
        Where where =
                Where.getInstance(sysDic).equal("sysDic.dicType", sysDic.getDicType())
                        .equal("sysDic.dicKey", sysDic.getDicKey())
                        .equal("sysDic.dicValue", sysDic.getDicValue());
        Order order = Order.getInstance().asc("sysDic.dicType").asc("sysDic.sort");
        Join join = Join.getInstance().join("creator").join("modifyMan");
        Pager<SysDic> pager = new Pager<SysDic>(pageNo, pageSize, where, order, join);

        pager = sysDicService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/dic/dic_list";
    }

    /**
     * <p>
     * description:添加页面
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute SysDic sysDic) {
        return "sys/dic/dic_add";
    }

    /**
     * <p>
     * description:添加数据
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute SysDic sysDic) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        Date today = new Date();
        sysDic.setCreator(sysUser);
        sysDic.setModifyMan(sysUser);
        sysDic.setCreateDate(today);
        sysDic.setModifyDate(today);
        Where where =
                Where.getInstance().equal("sysDic.dicType", sysDic.getDicType())
                        .equal("sysDic.dicKey", "#");
        SysDic sysDicTmp = sysDicService.find(where);
        if (sysDicTmp != null) {
            sysDicService.deleteAndAdd(sysDicTmp.getId(), sysDic);
        } else {
            sysDicService.add(sysDic);
        }

        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/dic/list";
    }

    /**
     * <p>
     * description:添加类型页面
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/addtype", method = RequestMethod.GET)
    public String toAddType(@ModelAttribute SysDic sysDic) {
        return "sys/dic/dic_add_type";
    }

    /**
     * <p>
     * description:添加类型数据
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/addtype", method = RequestMethod.POST)
    public String addType(@ModelAttribute SysDic sysDic) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        Date today = new Date();
        sysDic.setDicKey("#");
        sysDic.setDicValue("#");
        sysDic.setSort(0);
        sysDic.setCreator(sysUser);
        sysDic.setModifyMan(sysUser);
        sysDic.setCreateDate(today);
        sysDic.setModifyDate(today);
        sysDicService.add(sysDic);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/dic/list";
    }

    /**
     * <p>
     * description:修改页面
     * </p>
     * 
     * @param id
     * @param sysDic
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, @ModelAttribute SysDic sysDic)
            throws Exception {
        SysDic sysDicTemp = sysDicService.load(id);
        PropertyUtil.copyProperties(sysDic, sysDicTemp);
        return "sys/dic/dic_update";
    }

    /**
     * <p>
     * description:修改数据
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute SysDic sysDic) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        sysDic.setModifyMan(sysUser);
        sysDic.setModifyDate(new Date());
        sysDicService.update(sysDic, "dicType", "typeDesc", "dicKey", "dicValue", "sort",
                "modifyMan", "modifyDate");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/sys/dic/list";
    }

    /**
     * <p>
     * description:修改类型页面
     * </p>
     * 
     * @param id
     * @param sysDic
     * @return
     * @throws Exception
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/{id}/updatetype", method = RequestMethod.GET)
    public String toUpdateType(@PathVariable("id") Long id, @ModelAttribute SysDic sysDic)
            throws Exception {
        SysDic sysDicTemp = sysDicService.load(id);
        PropertyUtil.copyProperties(sysDic, sysDicTemp);
        return "sys/dic/dic_update_type";
    }

    /**
     * <p>
     * description:修改类型数据
     * </p>
     * 
     * @param sysDic
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/updatetype", method = RequestMethod.POST)
    public String updateType(@ModelAttribute SysDic sysDic) {
        SysDic sysDicTemp = sysDicService.load(sysDic.getId());
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        sysDicService.updateDicType(sysDicTemp.getDicType(), sysDic.getDicType(),
                sysDic.getTypeDesc());
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);
        return "redirect:/sys/dic/list";
    }

    /**
     * <p>
     * description:删除
     * </p>
     * 
     * @param id
     * @return
     * 
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        sysDicService.delete(id);

        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/sys/dic/list";

    }
}
