package cn.wzga.open.dao.sys.impl;

import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.sys.SysLoginLogDao;
import cn.wzga.open.entity.sys.SysLoginLog;


@Repository("sysLoginLogDao")
public class SysLoginLogDaoImpl extends BaseDaoImpl<SysLoginLog> implements SysLoginLogDao{

}
