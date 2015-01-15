package cn.wzga.open.entity.sys;

import cn.wzga.open.cache.SysDicCache;
import cn.wzga.open.util.WebConstant;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_department")
public class SysDepartment implements Serializable {
    private Long id;// 关键字
    private String deptCode;// 单位代码
    private String deptName;// 单位名称
    private String deptSpell;// 单位拼音
    private Integer deptLevel;// 单位层次
    private String otherName;// 其他名称
    private String simpleName;// 简称
    private Integer isFormal;// 是否正式机构
    private Date enableDate;// 启用日期
    private Integer isEnable;// 是否启用（默认为0：否，1：是）
    private Date disableDate;// 停用日期
    private Integer isStopped;// 是否停用 暂时不用，isEnable 代替
    private String originalCode;// 原机构代码
    private Date originalStoppedDate;// 原机构停用日期

    @Id
    @SequenceGenerator(name = "s_sys_department", sequenceName = "s_sys_department", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_department")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "dept_code")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "dept_spell")
    public String getDeptSpell() {
        return deptSpell;
    }

    public void setDeptSpell(String deptSpell) {
        this.deptSpell = deptSpell;
    }

    @Column(name = "dept_level")
    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Column(name = "other_name")
    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    @Column(name = "simple_name")
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Column(name = "is_formal")
    public Integer getIsFormal() {
        return isFormal;
    }

    public void setIsFormal(Integer isFormal) {
        this.isFormal = isFormal;
    }

    @Column(name = "enable_date")
    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Column(name = "is_enable")
    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    @Column(name = "disable_date")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @Column(name = "is_stopped")
    public Integer getIsStopped() {
        return isStopped;
    }

    public void setIsStopped(Integer isStopped) {
        this.isStopped = isStopped;
    }

    @Column(name = "original_code")
    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    @Column(name = "original_stopped_date")
    public Date getOriginalStoppedDate() {
        return originalStoppedDate;
    }

    public void setOriginalStoppedDate(Date originalStoppedDate) {
        this.originalStoppedDate = originalStoppedDate;
    }

    @Transient
    public String getIsEnableStr() {
        if (this.isEnable != null) {
            return SysDicCache.get(WebConstant.DEPARTMENT_DIC_TYP, String.valueOf(this.isEnable));
        }
        return null;
    }

    @Transient
    public String getIsFormalStr() {
        if (this.isFormal != null) {
            return SysDicCache.get(WebConstant.DEPARTMENT_DIC_TYP, String.valueOf(this.isFormal));
        }
        return null;
    }

    @Transient
    public String getIsStoppedStr() {
        if (this.isStopped != null) {
            return SysDicCache.get(WebConstant.DEPARTMENT_DIC_TYP, String.valueOf(this.isStopped));
        }
        return null;
    }
}
