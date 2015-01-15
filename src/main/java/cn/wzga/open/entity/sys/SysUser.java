package cn.wzga.open.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
    private Long id;
    private String loginName;// 登录名
    private String password;// 密码
    private String chineseName;// 中文名
    private String identifyCode;// 身份证
    private String policeCode;// 警号
    private String phoneNumber;// 手机号码
    private String email;// 邮箱
    private SysUser creator;// 创建人
    private Date createDate;// 创建时间
    private SysUser modifyMan;// 修改人
    private Date modifyDate;// 修改时间
    private Integer state = 1;// 是否删除

    private SysDepartment sysDepartment;

    private Set<SysRole> roleList;// 角色列表
    private String deptCode;// 单位代码(冗余字段)
    protected String roleIds;// 角色内部Id

    private Set<SysResource> resourceList;// 资源列表

    @Id
    @SequenceGenerator(name = "s_sys_user", sequenceName = "s_sys_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_user")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "identify_code")
    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    @Column(name = "police_code")
    public String getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "dept_code")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    public SysDepartment getSysDepartment() {
        return sysDepartment;
    }

    public void setSysDepartment(SysDepartment sysDepartment) {
        this.sysDepartment = sysDepartment;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public Set<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Transient
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Transient
    public Set<SysResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(Set<SysResource> resourceList) {
        this.resourceList = resourceList;
    }

}
