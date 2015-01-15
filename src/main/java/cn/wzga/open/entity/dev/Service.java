package cn.wzga.open.entity.dev;

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

import cn.wzga.open.entity.sys.SysUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_service")
public class Service implements Serializable {
    private Long id;// 关键字
    private String serName;// 名称
    private String serId;// 服务id
    private String serSecret;// 服务secret
    private SysUser creator;// 创建人
    private Date createDate;// 创建时间
    private SysUser modifyMan;// 修改人
    private Date modifyDate;// 修改时间
    private Integer state = 1;// 伪删除状态（1：正常，2：删除）

    @Id
    @SequenceGenerator(name = "s_tbl_service", sequenceName = "s_tbl_service", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_tbl_service")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ser_name")
    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    @Column(name = "ser_id")
    public String getSerId() {
        return serId;
    }

    public void setSerId(String serId) {
        this.serId = serId;
    }

    @Column(name = "ser_secret")
    public String getSerSecret() {
        return serSecret;
    }

    public void setSerSecret(String serSecret) {
        this.serSecret = serSecret;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    public SysUser getCreator() {
        return creator;
    }

    public void setCreator(SysUser creator) {
        this.creator = creator;
    }

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_man")
    public SysUser getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(SysUser modifyMan) {
        this.modifyMan = modifyMan;
    }

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
