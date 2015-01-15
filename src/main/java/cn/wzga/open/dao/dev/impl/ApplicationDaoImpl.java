package cn.wzga.open.dao.dev.impl;

import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.dev.ApplicationDao;
import cn.wzga.open.entity.dev.Application;

@Repository("applicationDao")
public class ApplicationDaoImpl extends BaseDaoImpl<Application> implements ApplicationDao {

}
