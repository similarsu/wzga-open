package cn.wzga.open.entity.sys;

import cn.wzga.open.entity.AbstractBaseDeletableEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_resource")
public class SysResource extends AbstractBaseDeletableEntity implements Serializable {
    private Long id;
    private String name;// 名称
    private String url;// 网址
    private Integer type;// 类别（1：菜单，2。功能）
    private Integer resLevel;// 资源级别
    private Integer sort;// 排序
    private String icon;// 默认图标
    private String iconOpen;// 展开图标
    private String iconClose;// 关闭图标

    private SysResource parent;// 父亲节点

    private Set<SysResource> childList;

    protected String parentId;// parent id

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_resource")
    @SequenceGenerator(name = "s_sys_resource", sequenceName = "s_sys_resource", allocationSize = 1)
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

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "res_level")
    public Integer getResLevel() {
        return resLevel;
    }

    public void setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "icon_open")
    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    @Column(name = "icon_close")
    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public SysResource getParent() {
        return parent;
    }

    public void setParent(SysResource parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    public Set<SysResource> getChildList() {
        return childList;
    }

    public void setChildList(Set<SysResource> childList) {
        this.childList = childList;
    }

    @Transient
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SysResource that = (SysResource) o;

        if (!id.equals(that.id))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
