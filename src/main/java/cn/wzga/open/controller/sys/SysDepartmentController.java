package cn.wzga.open.controller.sys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Pager;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.Constant;
import cn.wzga.core.util.Messages;
import cn.wzga.core.util.PropertyUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.util.WebConstant;

/**
 * <p>
 * description:单位代码管理控制层
 * </p>
 * 
 * @author chenjingchai
 * @version v1.0
 * @since 2014年11月3日
 */
@Controller
@RequestMapping(value = "/sys/department")
public class SysDepartmentController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDepartmentController.class);

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
     * @param sysDepartment
     * @return
     * @throws Exception
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constant.PAGE_SIZE) int pageSize,
            ModelMap modelMap, @ModelAttribute SysDepartment sysDepartment) throws Exception {
        Where where =
                Where.getInstance(sysDepartment).prefixLike("deptCode").prefixLike("deptName");
        Order order = Order.getInstance().asc("deptCode");
        Pager<SysDepartment> pager = new Pager<SysDepartment>(pageNo, pageSize, where, order);

        pager = sysDepartmentService.findPage(pager);

        modelMap.put("pager", pager);

        return "/sys/department/department_list";
    }

    /**
     * <p>
     * description:添加页面
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute SysDepartment sysDepartment) {
        return "sys/department/department_add";
    }

    /**
     * <p>
     * description:添加数据
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute SysDepartment sysDepartment) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        sysDepartmentService.add(sysDepartment);
        Messages.setSuccessMessage(ADD_SUCCESS_MSG);

        return "redirect:/sys/department/list";
    }

    /**
     * <p>
     * description:修改页面
     * </p>
     * 
     * @param id
     * @param sysDepartment
     * @return
     * @throws Exception
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Long id, @ModelAttribute SysDepartment sysDepartment)
            throws Exception {
        SysDepartment sysDepartmentTemp = sysDepartmentService.load(id);
        PropertyUtil.copyProperties(sysDepartment, sysDepartmentTemp);
        return "sys/department/department_update";
    }

    /**
     * <p>
     * description:修改数据
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月3日
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute SysDepartment sysDepartment) {
        SysUser sysUser = this.getSysUserSession();
        if (sysUser == null) {
            throw new RuntimeException("error.sys.interior");
        }
        sysDepartmentService.update(sysDepartment, "deptCode", "deptName", "deptSpell",
                "deptLevel", "otherName", "simpleName", "isFormal", "originalCode",
                "originalStoppedDate");
        Messages.setSuccessMessage(UPDATE_SUCCESS_MSG);

        return "redirect:/sys/department/list";
    }

    /**
     * <p>
     * description:启用组织机构
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月18日
     */
    @RequestMapping(value = "/{id}/enable", method = RequestMethod.GET)
    public String enable(@PathVariable("id") Long id, ModelMap modelMap,
            @ModelAttribute SysDepartment sysDepartment) throws Exception {
        SysDepartment sysDepartmentTemp = sysDepartmentService.load(id);
        sysDepartmentTemp.setEnableDate(new Date());
        sysDepartmentTemp.setIsEnable(WebConstant.DEPARTMENT_ENABLE_YES);
        sysDepartmentTemp.setIsStopped(WebConstant.DEPARTMENT_ENABLE_NO);
        sysDepartmentService.update(sysDepartmentTemp, "enableDate", "isEnable", "isStopped");
        Messages.setSuccessMessage("success.department.enable");

        return "redirect:/sys/department/list";
    }

    /**
     * <p>
     * description:停用组织机构
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月18日
     */
    @RequestMapping(value = "/{id}/stop", method = RequestMethod.GET)
    public String stop(@PathVariable("id") Long id, ModelMap modelMap,
            @ModelAttribute SysDepartment sysDepartment) throws Exception {
        SysDepartment sysDepartmentTemp = sysDepartmentService.load(id);
        sysDepartmentTemp.setDisableDate(new Date());
        sysDepartmentTemp.setIsEnable(WebConstant.DEPARTMENT_ENABLE_NO);
        sysDepartmentTemp.setIsStopped(WebConstant.DEPARTMENT_ENABLE_YES);
        sysDepartmentService.update(sysDepartmentTemp, "disableDate", "isEnable", "isStopped");
        Messages.setSuccessMessage("success.department.stop");
        return "redirect:/sys/department/list";
    }

    /**
     * <p>
     * description:查看组织机构
     * </p>
     * 
     * @param sysDepartment
     * @return
     * @author chenjingchai
     * @since 2014年11月18日
     */
    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, ModelMap modelMap,
            @ModelAttribute SysDepartment sysDepartment) throws Exception {
        SysDepartment sysDepartmentTemp = sysDepartmentService.load(id);

        PropertyUtil.copyProperties(sysDepartment, sysDepartmentTemp);

        return "sys/department/department_detail";
    }

    /**
     * <p>
     * description:删除
     * </p>
     * 
     * @param id
     * @return
     * @author chenjingchai
     * @author cl
     * @since 2014/12/24
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable(value = "id") Long id) {
        sysDepartmentService.delete(id);
        Messages.setSuccessMessage(DELETE_SUCCESS_MSG);

        return "redirect:/sys/department/list";

    }

    /**
     * <p>
     * description:导出excel
     * </p>
     * 
     * @param response
     * @author czt
     * @since 2014年12月30日
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) {
        Workbook workbook = sysDepartmentService.getExportContent();
        if (null == workbook) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        StringBuilder contentDisposition = new StringBuilder("attachment;filename=\"");
        contentDisposition.append("zzjg-");
        contentDisposition.append(new SimpleDateFormat("yyyy_MM_dd").format(new Date()));
        contentDisposition.append(".xls\"");

        response.setHeader("content-disposition", contentDisposition.toString());
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("department:exportExcel", e);
            }
        }
    }
}
