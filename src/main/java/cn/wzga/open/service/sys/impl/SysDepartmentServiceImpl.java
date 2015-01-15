package cn.wzga.open.service.sys.impl;

import cn.wzga.core.dao.base.support.Order;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.core.util.StringUtil;
import cn.wzga.open.dao.sys.SysDepartmentDao;
import cn.wzga.open.entity.sys.SysDepartment;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.util.WebConstant;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service("sysDepartmentService")
@Transactional
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment>
        implements
        SysDepartmentService {
    @Resource(name = "sysDepartmentDao")
    private SysDepartmentDao sysDepartmentDao;

    @PostConstruct
    public void injectBaseDao() {
        super.injectBaseDao(sysDepartmentDao);
    }

    @Override
    public Serializable add(SysDepartment sysDepartment) {
        sysDepartment.setIsEnable(WebConstant.DEPARTMENT_ENABLE_NO);
        sysDepartment.setIsStopped(WebConstant.DEPARTMENT_ENABLE_YES);
        sysDepartmentDao.save(sysDepartment);
        return sysDepartment.getId();
    }

    @Override
    public SysDepartment findByCode(String deptCode) {
        return sysDepartmentDao.find(Where.getInstance().equal("sysDepartment.deptCode", deptCode));
    }

    @Override
    public Workbook getExportContent() {
        Where where = Where.getInstance().equal("sysDepartment.deptLevel", 4);
        Order order = Order.getInstance().asc("sysDepartment.deptCode");
        List<SysDepartment> deptList = sysDepartmentDao.findAll(where, order);
        if (deptList == null || deptList.size() == 0) {
            return null;
        }
        HSSFWorkbook xlsFile = new HSSFWorkbook();
        // 创建单元格样式
        CellStyle stringStyle = xlsFile.createCellStyle();
        stringStyle.setAlignment(CellStyle.ALIGN_CENTER);
        CellStyle doubleStyle = xlsFile.createCellStyle();
        doubleStyle.setAlignment(CellStyle.ALIGN_LEFT);
        doubleStyle.setDataFormat(xlsFile.createDataFormat().getFormat("#.##"));
        CellStyle intStyle = xlsFile.createCellStyle();
        intStyle.setAlignment(CellStyle.ALIGN_LEFT);
        intStyle.setDataFormat(xlsFile.createDataFormat().getFormat("0"));
        CellStyle dateStyle = xlsFile.createCellStyle();
        dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
        dateStyle.setDataFormat(xlsFile.createDataFormat().getFormat("yyyyMMdd"));

        Sheet sheet = xlsFile.createSheet("组织机构");
        createRow(xlsFile, sheet, 0, stringStyle, null, null, null, "机构序列", "机构代码", "机构名称",
                "机构其他名称", "机构简称", "是否正式机构", "启用日期", "停用日期", "是否停用", "原机构代码", "原机构代码停用日期",
                "上级业务主管单位代码", "上级业务主管单位名称", "当地业务主管单位代码");
        int count = 0;
        SysDepartment dept = null;

        stringStyle.setAlignment(CellStyle.ALIGN_LEFT);
        for (int i = 0; i < deptList.size(); i++) {
            count = i + 1;
            dept = deptList.get(i);
            createRow(xlsFile, sheet, count, stringStyle, doubleStyle, intStyle, dateStyle, count,
                    dept.getDeptCode(), dept.getDeptName(), dept.getOtherName(),
                    dept.getSimpleName(), dept.getIsFormal(), dept.getEnableDate(),
                    dept.getDisableDate(), dept.getIsStopped(), dept.getOriginalCode(),
                    dept.getOriginalStoppedDate());
        }
        return xlsFile;
    }

    @Override
    public SysDepartment queryDepartmentByDeptCodeNotFixed(String deptCode) {
        /*
        while true:
            if blank = deptCdoe:
                break;
            if in db:
                return deptCode;

            up level deptcode
         */
        StringBuilder dp = new StringBuilder(deptCode);
        while (true) {
            if (1 > dp.length()) {
                break;
            }

            SysDepartment sysDepartment =
                    sysDepartmentDao.find(Where.getInstance().equal("deptCode", dp.toString()));
            if (null != sysDepartment) {
                return sysDepartment;
            }

            int deptCodeEnd = dp.length();
            if (6 < deptCodeEnd) {
                deptCodeEnd = 6;

            } else if (4 < deptCodeEnd) {
                deptCodeEnd = 4;

            } else if (2 < deptCodeEnd) {
                deptCodeEnd = 2;

            } else {
                break;

            }

            dp.delete(deptCodeEnd, dp.length());
        }

        return null;
    }

    protected void createRow(Workbook wb, Sheet sheet, int rowInx, CellStyle stringStyle,
                             CellStyle doubleStyle, CellStyle intStyle, CellStyle dateStyle, Object... cells) {
        Row row = sheet.createRow(rowInx);
        for (int i = 0; i < cells.length; i++) {
            Cell cell = row.createCell(i);
            Object val = cells[i];
            if (val instanceof String) {
                cell.setCellStyle(stringStyle);
                cell.setCellValue((String) val);
            } else if (val instanceof Double) {
                cell.setCellStyle(doubleStyle);
                cell.setCellValue((Double) val);
            } else if (val instanceof Date) {
                cell.setCellStyle(dateStyle);
                cell.setCellValue((Date) val);
            } else if (val instanceof Integer) {
                cell.setCellStyle(intStyle);
                cell.setCellValue((Integer) val);
            }
        }

    }
}
