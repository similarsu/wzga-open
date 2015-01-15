package cn.wzga.open.service.sys.impl;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.dao.base.support.Join;
import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.sys.SysLoginLogDao;
import cn.wzga.open.dao.sys.SysResourceDao;
import cn.wzga.open.dao.sys.SysRoleDao;
import cn.wzga.open.dao.sys.SysUserDao;
import cn.wzga.open.entity.AbstractBaseDeletableEntity;
import cn.wzga.open.entity.sys.SysLoginLog;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;
import cn.wzga.open.service.sys.SysDepartmentService;
import cn.wzga.open.service.sys.SysUserService;
import cn.wzga.open.util.WebConstant;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Resource(name = "sysLoginLogDao")
    private SysLoginLogDao sysLoginLogDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysResourceDao sysResourceDao;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @PostConstruct
    public void injectBaseDao() {
        super.injectBaseDao(sysUserDao);
    }

    /**
     * 通过用户名密码登录
     * 
     * @param loginName
     * @param password
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public SysUser findByLoginNamePassword(String loginName, String password) {
        Where where =
                Where.getInstance().equal("sysUser.loginName", loginName)
                        .equal("sysUser.password", password);

        return this.findSysUser(where);
    }

    /**
     * 通过身份证登录
     * 
     * @param identifyCode
     * @return
     */
    @Override
    public SysUser findByIdentifyCode(String identifyCode) {
        Where where = Where.getInstance().equal("sysUser.identifyCode", identifyCode);
        return this.findSysUser(where);
    }

    /**
     * PKI注册用户带日志
     * 
     * @param sysUser
     * @param ip
     */
    @Override
    public void register(SysUser sysUser, String ip) {
        sysUserDao.save(sysUser);
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIdentifyCode(sysUser.getIdentifyCode());
        sysLoginLog.setName(sysUser.getChineseName());
        sysLoginLog.setLogDate(new Date());
        sysLoginLog.setIp(ip);
        sysLoginLog.setStatus(WebConstant.LOGIN_REGISTER); // pki注册
        sysLoginLog.setCreator(sysUser);
        sysLoginLog.setCreateUnit(sysUser.getSysDepartment().getDeptName());
        sysLoginLog.setCreateUnitCode(sysUser.getSysDepartment().getDeptCode());
        sysLoginLogDao.save(sysLoginLog);
    }

    @Override
    public SysUser findModifyManByRole(SysRole role) {
        if (null == role || null == role.getId()) {
            return null;
        }

        try {
            SysRole queriedRole =
                    sysRoleDao.find(Where.getInstance(role).equal("id"),
                            Join.getInstance().join("modifyMan"));

            if (null == queriedRole) {
                return null;

            }

            return queriedRole.getModifyMan();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("find role", e);
            }
        }
        return null;
    }

    /**
     * <p>
     * 查找用户和用户关联的资源列表
     * </p>
     * 
     * @param where
     * @return
     * @author cl
     * @since 2014/12/25
     */
    private SysUser findSysUser(Where where) {
        Join join = Join.getInstance().join("sysDepartment").join("roleList");
        SysUser sysUser = sysUserDao.find(where, join);
        if (sysUser != null) {
            sysUser.setResourceList(this.queryCurrentUserResources(sysUser));

        }
        return sysUser;

    }

    /**
     * <p>
     * 根据用户查找相应权限的资源列表
     * </p>
     * <p>
     * 1. 用户本身创建的资源列表 2. 用户被授权的权限列表 3. 如果是超级管理员， 则获取授权给他的账号的所有资源列表
     * </p>
     * 
     * @param sysUser
     * @return
     * @author cl
     * @since 2014/12/25
     */
    public Set<SysResource> queryCurrentUserResources(SysUser sysUser) {
        /*
         * if current user is admin query all resources by the role creator else: query all reousrce
         * in the role of current user
         */
        SysRole adminRole = queryAdminRole(sysUser);

        // declare vars
        Set<SysResource> resourceList = null;

        // 当前用户所有资源
        setUpSysUserResources(sysUser);

        // is admin
        if (null != adminRole) {
            SysUser roleCreator = this.findModifyManByRole(adminRole);
            if (null != roleCreator) {
                setUpSysUserResources(roleCreator);
                resourceList = roleCreator.getResourceList();

            }

        }

        if (null != resourceList) {
            Set<SysResource> tmpRes = sysUser.getResourceList();
            if (!CollectionUtils.isEmpty(tmpRes)) {
                resourceList.addAll(tmpRes);

            }

        } else {
            resourceList = sysUser.getResourceList();

        }

        return resourceList;
    }

    public SysRole queryAdminRole(SysUser sysUser) {
        Set<SysRole> roles = sysUser.getRoleList();
        if (!CollectionUtils.isEmpty(roles)) {
            for (SysRole role : roles) {
                if (SysRole.IS_ADMIN_TRUE == role.getIsAdmin()) {
                    return role;
                }
            }
        }

        return null;

    }

    /**
     * <p>
     * 查找用户关联的角色的资源列表和用户自己创建的资源链表
     * </p>
     * 
     * @param sysUser
     * @author cl
     * @since 2014/12/25
     */
    protected void setUpSysUserResources(SysUser sysUser) {
        // query resources by roles
        Set<SysRole> roleList = sysUser.getRoleList();
        Set<SysResource> resList = null;
        if (!CollectionUtils.isEmpty(roleList)) {
            resList = new LinkedHashSet<SysResource>();
            for (SysRole sysRole : roleList) {
                Set<SysResource> sysResourceList = sysRole.getResourceList();

                if (!CollectionUtils.isEmpty(sysResourceList)) {
                    resList.addAll(sysResourceList);
                }
            }

        }

        // query resources by modifyMan
        List<SysResource> selfModifiedRes =
                sysResourceDao.findAll(Where.getInstance().equal("modifyMan.id", sysUser.getId())
                        .equal("state ", AbstractBaseDeletableEntity.ENTITY_STATE_NORMAL));

        if (!CollectionUtils.isEmpty(selfModifiedRes)) {
            if (null == resList) {
                resList = new LinkedHashSet<SysResource>();
            }

            resList.addAll(selfModifiedRes);

        }

        if (!CollectionUtils.isEmpty(resList)) {
            sysUser.setResourceList(resList);

        }

    }

}
