package cn.wzga.open.controller.ajax;

import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.controller.BaseController;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.util.WebConstant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description:异步获取单位信息
 * </p>
 *
 * @author chenjingchai
 * @version v1.0
 * @since 2014年11月4日
 */
@Controller
@RequestMapping(value = "/ajax/dept")
public class AjaxDeptController extends BaseController {
    public static final String DEFAULT_DEPT_CODE = "3303";

    @Resource(name = "sysDepartmentService")
    private SysDepartmentService sysDepartmentService;

    @RequestMapping("/{code}/init")
    public String view(ModelMap mm, @PathVariable("code") String code) {
        if (code.equals("-1")) {
            code = DEFAULT_DEPT_CODE;
        }

        String provinceCode,
                cityCode,
                countyCode;

        if (!StringUtil.isBlank(code)) {
            int codeLen = code.length();

            if (1 < codeLen) {
                provinceCode = code.substring(0, 2);
                mm.put("provinceCode", provinceCode);
                mm.put("provinceList", getProvince());
                mm.put("cityList", getCityByProvinceCode(provinceCode));

            }

            if (3 < codeLen) {
                cityCode = code.substring(0, 4);
                mm.put("cityCode", cityCode);
                mm.put("countyList", getCountyByCityCode(cityCode));

            }

            if (5 < codeLen) {
                countyCode = code.substring(0, 6);
                mm.put("countyCode", countyCode);
                mm.put("deptList", getDeptByCountyCode(countyCode));

            }

            if (11 < codeLen) {
                mm.put("deptCode", code);

            }
        }

        return "ajax/dept/ajax_dept";
    }

    @RequestMapping(value = "/province", method = RequestMethod.GET)
    @ResponseBody
    public List<SysDepartment> getProvince() {
        Where where = Where.getInstance().equal("sysDepartment.deptLevel", 1)
                .equal("isEnable", WebConstant.DEPARTMENT_ENABLE_YES);
        Order order = Order.getInstance().asc("deptCode");
        return sysDepartmentService.findAll(where, order);
    }

    @RequestMapping(value = "/{code}/city")
    @ResponseBody
    public List<SysDepartment> getCityByProvinceCode(@PathVariable("code") String code) {
        if (code.equals("-1")) {
            code = null;
        }
        Where where =
                Where.getInstance().equal("sysDepartment.deptLevel", 2)
                        .prefixLike("sysDepartment.deptCode", code)
                        .equal("isEnable", WebConstant.DEPARTMENT_ENABLE_YES);
        Order order = Order.getInstance().asc("deptCode");
        return sysDepartmentService.findAll(where, order);
    }

    @RequestMapping(value = "/{code}/county")
    @ResponseBody
    public List<SysDepartment> getCountyByCityCode(@PathVariable("code") String code) {
        if (code.equals("-1")) {
            code = null;
        }
        Where where =
                Where.getInstance().equal("sysDepartment.deptLevel", 3)
                        .prefixLike("sysDepartment.deptCode", code)
                        .equal("isEnable", WebConstant.DEPARTMENT_ENABLE_YES);
        Order order = Order.getInstance().asc("deptCode");
        return sysDepartmentService.findAll(where, order);
    }

    @RequestMapping(value = "/{code}/dept")
    @ResponseBody
    public List<SysDepartment> getDeptByCountyCode(@PathVariable("code") String code) {
        if (code.equals("-1")) {
            code = null;
        }
        Where where =
                Where.getInstance().equal("sysDepartment.deptLevel", 4)
                        .prefixLike("sysDepartment.deptCode", code)
                        .equal("isEnable", WebConstant.DEPARTMENT_ENABLE_YES);
        Order order = Order.getInstance().asc("deptCode");
        return sysDepartmentService.findAll(where, order);
    }

    @RequestMapping(value = "/isExists", method = RequestMethod.POST)
    public void deptCodeExists(HttpServletResponse response) throws IOException {
        response.setContentType("text/html);charset=utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.addHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        String deptCode = super.getRequest().getParameter("deptCode");
        SysDepartment sysDepartment = null;
        if (!StringUtil.isBlank(deptCode)) {
            Where where = Where.getInstance().equal("sysDepartment.deptCode", deptCode);
            sysDepartment = sysDepartmentService.find(where);
        }

        if (sysDepartment == null) {
            out.print("true");
        } else {
            out.print("false");
        }
    }

    /**
     * <p>根据部门代码查找部门信息</p>
     *
     * @param code
     * @return 部门列表 {list: [{key: xxx, value: xxx}, ....]}
     * @author cl
     * @since 2015/01/12
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<Map<String, String>>> queryDeptByDeptCode(@RequestParam(value = "q") String code) {
        Where where =
                Where.getInstance()
                        .equal("sysDepartment.deptCode", code)
                        .equal("isEnable", WebConstant.DEPARTMENT_ENABLE_YES);

        SysDepartment sysDepartment = sysDepartmentService.find(where);
        if (null == sysDepartment) {
            return null;
        }

        List<Map<String, String>> result = new ArrayList<Map<String, String>>(1);
        Map<String, String> obj = new HashMap<String, String>(2);
        obj.put("key", sysDepartment.getDeptName());
        obj.put("value", sysDepartment.getDeptCode());
        result.add(obj);

        Map<String, List<Map<String, String>>> res =
                new HashMap<String, List<Map<String, String>>>(2);
        res.put("list", result);
        return res;
    }
}
