package cn.wzga.open.dao.sys.impl;

import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.sys.SysUserDao;
import cn.wzga.open.entity.sys.SysUser;


@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao{

}
