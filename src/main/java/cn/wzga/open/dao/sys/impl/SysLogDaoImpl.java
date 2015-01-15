package cn.wzga.open.dao.sys.impl;

import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.sys.SysLogDao;
import cn.wzga.open.entity.sys.SysLog;


@Repository("sysLogDao")
public class SysLogDaoImpl extends BaseDaoImpl<SysLog> implements SysLogDao{

}
