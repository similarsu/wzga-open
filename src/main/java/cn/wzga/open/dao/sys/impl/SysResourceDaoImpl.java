package cn.wzga.open.dao.sys.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.wzga.core.dao.base.impl.BaseDaoImpl;
import cn.wzga.open.dao.sys.SysResourceDao;
import cn.wzga.open.entity.sys.SysResource;


@Repository("sysResourceDao")
public class SysResourceDaoImpl extends BaseDaoImpl<SysResource> implements SysResourceDao{

	@Override
	public void deleteRelation(Serializable id) {
		// TODO Auto-generated method stub
		Session session=getSession();
		session.createSQLQuery("delete from sys_role_resource where res_id=?").setLong(0, (Long)id).executeUpdate();
	}

}
