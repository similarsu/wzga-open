package cn.wzga.open.entity.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_login_log")
public class SysLoginLog implements Serializable {
    private Long id;
    private String identifyCode;// 身份证
    private String name;// 姓名
    private SysUser creator;// 登录人
    private Date logDate;// 登录时间
    private String ip;// 客户端ip
    private Integer status;// 状态：1、登录，2、退出
    private String createUnit;// 单位名称
    private String createUnitCode;// 单位代码

    @Id
    @SequenceGenerator(name = "s_sys_login_log", sequenceName = "s_sys_login_log", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_login_log")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "identify_code")
    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "log_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    public SysUser getCreator() {
        return creator;
    }

    public void setCreator(SysUser creator) {
        this.creator = creator;
    }

    @Column(name = "create_unit")
    public String getCreateUnit() {
        return createUnit;
    }

    public void setCreateUnit(String createUnit) {
        this.createUnit = createUnit;
    }

    @Column(name = "create_unit_code")
    public String getCreateUnitCode() {
        return createUnitCode;
    }

    public void setCreateUnitCode(String createUnitCode) {
        this.createUnitCode = createUnitCode;
    }

}
