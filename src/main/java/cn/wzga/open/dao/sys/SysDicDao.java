package cn.wzga.open.dao.sys;

import java.util.List;

import cn.wzga.core.dao.base.BaseDao;
import cn.wzga.open.entity.sys.SysDic;


public interface SysDicDao  extends BaseDao<SysDic>{

	public List<SysDic> findByGroup();

	public void updateDicType(String oldType, String newType, String newTypeDesc);

}
