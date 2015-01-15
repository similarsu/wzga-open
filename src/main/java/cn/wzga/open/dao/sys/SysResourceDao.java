package cn.wzga.open.dao.sys;

import java.io.Serializable;

import cn.wzga.core.dao.base.BaseDao;
import cn.wzga.open.entity.sys.SysResource;

public interface SysResourceDao extends BaseDao<SysResource>{

	void deleteRelation(Serializable id);

}
