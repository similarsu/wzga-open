package cn.wzga.open.entity.sys;

import cn.wzga.open.entity.AbstractBaseDeletableEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role")
public class SysRole extends AbstractBaseDeletableEntity implements Serializable {
    public static final Integer IS_ADMIN_TRUE = 1;
    public static final Integer IS_ADMIN_FALSE = 0;

    private Long id;// 关键字
    private String name;// 名称
    private String remark;// 备注
    private Integer isAdmin;

    private Set<SysResource> resourceList;// 权限列表

    @Id
    @SequenceGenerator(name = "s_sys_role", sequenceName = "s_sys_role", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_role")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToMany
    @JoinTable(name = "sys_role_resource", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "res_id", referencedColumnName = "id")})
    public Set<SysResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(Set<SysResource> resourceList) {
        this.resourceList = resourceList;
    }

    /**
     * <p>是否管理员权限, 管理员权限能管理当前角色的所有权限，忽略当前角色的权限限制</p>
     * <p>{@link cn.wzga.open.entity.sys.SysRole#IS_ADMIN_FALSE}, {@link cn.wzga.open.entity.sys.SysRole#IS_ADMIN_TRUE}</p>
     *
     * @return
     * @author cl
     * @since 2014/12/17
     */
    @Column(name = "IS_ADMIN")
    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SysRole sysRole = (SysRole) o;

        if (!id.equals(sysRole.id))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
