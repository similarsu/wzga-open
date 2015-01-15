package cn.wzga.open.service.sys;

import cn.wzga.core.service.base.BaseService;
import cn.wzga.open.entity.sys.SysDepartment;

import org.apache.poi.ss.usermodel.Workbook;

public interface SysDepartmentService extends BaseService<SysDepartment> {
    /**
     * 根据代码获取单位
     *
     * @param deptCode
     * @return
     */
    public SysDepartment findByCode(String deptCode);

    /**
     * <p>
     * description:生成csv格式的组织机构列表
     * </p>
     *
     * @return
     * @author czt
     * @since 2014年12月30日
     */
    public Workbook getExportContent();

    /**
     * <p>根据部门code，查找数据库部门表，如不存在则上升到父部门，知道最终</p>
     *
     * @param deptCode
     * @return 在sys_deparment表中存在的部门信息
     * @author cl
     * @since 2015/01/08
     */
    public SysDepartment queryDepartmentByDeptCodeNotFixed(String deptCode);
}
