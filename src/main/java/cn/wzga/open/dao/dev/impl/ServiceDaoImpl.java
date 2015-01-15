package cn.wzga.open.dao.dev.impl;

import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.dev.ServiceDao;
import cn.wzga.open.entity.dev.Service;

@Repository("serviceDao")
public class ServiceDaoImpl extends BaseDaoImpl<Service> implements ServiceDao {

}
