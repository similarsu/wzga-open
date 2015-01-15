package cn.wzga.open.service.sys;

import java.util.Set;

import cn.wzga.core.service.base.BaseService;
import cn.wzga.open.entity.sys.SysResource;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.entity.sys.SysUser;

/**
 * <p>
 * description:用户管理服务层接口类
 * </p>
 * 
 * @author chenjingchai
 * @version v1.0
 * @since 2014年10月28日
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * <p>
     * description:根据用户名密码查询得到用户实体
     * </p>
     * 
     * @param loginName
     * @param password
     * @return SysUser
     * @author chenjingchai
     * @since 2014年10月28日
     */
    public SysUser findByLoginNamePassword(String loginName, String password);

    /**
     * <p>
     * description:根据身份证查询得到用户实体类
     * </p>
     * 
     * @param identifyCode
     * @return SysUser
     * @author chenjingchai
     * @since 2014年10月28日
     */
    public SysUser findByIdentifyCode(String identifyCode);

    /**
     * <p>
     * description:注册用户并且记录日志
     * </p>
     * 
     * @param sysUser
     * @param ip
     * @author chenjingchai
     * @since 2014年10月28日
     */
    public void register(SysUser sysUser, String ip);

    /**
     * <p>
     * 根据角色查找角色修改者用户实体，慎用
     * </p>
     * <
     * 
     * @param role
     * @return
     * @author cl
     * @since 2014/12/17
     */
    public SysUser findModifyManByRole(SysRole role);

    /**
     * <p>
     * 查询用户的所有资源
     * </p>
     * 
     * @param sysUser
     * @return
     * @author cl
     * @since 2014/12/17
     */
    public Set<SysResource> queryCurrentUserResources(SysUser sysUser);

    /**
     * <p>
     * 根据用户查询管理员角色
     * </p>
     * 
     * @param sysUser
     * @return
     * @author cl
     * @since 2014/12/17
     */
    public SysRole queryAdminRole(SysUser sysUser);

}
