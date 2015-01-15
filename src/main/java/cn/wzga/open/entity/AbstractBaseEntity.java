package cn.wzga.open.entity;

import cn.wzga.open.entity.sys.SysUser;

import javax.persistence.*;

import java.util.Date;

/**
 * <p><strong>不对应任何表</strong>, 基础实体，包含创建人，创建时间，修改人修改时间，状态</p>
 *
 * @author cl
 * @since 2014/12/05
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractBaseEntity {

    private SysUser creator; //创建人
    private Date createDate; //创建时间
    private SysUser modifyMan; //修改人
    private Date modifyDate; //修改时间

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

}
