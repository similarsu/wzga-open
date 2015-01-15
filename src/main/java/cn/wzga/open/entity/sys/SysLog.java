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
@Table(name = "sys_log")
public class SysLog implements Serializable {
    private Long id;
    private String identifyCode;// 身份证
    private String name;// 姓名
    private SysUser creator;// 操作人
    private Date operateDate;// 操作时间
    private String operateModule;// 操作方式
    private String operateContent;// 操作内容
    private String ip;// 客户端ip
    private String createUnit;// 单位名称
    private String createUnitCode;// 单位代码

    @Id
    @SequenceGenerator(name = "s_sys_log", sequenceName = "s_sys_log", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_log")
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

    @Column(name = "operate_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Column(name = "operate_module")
    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    @Column(name = "operate_content")
    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
