package cn.wzga.open.service.sys;

import java.io.Serializable;
import java.util.List;

import cn.wzga.core.service.base.BaseService;
import cn.wzga.open.entity.sys.SysDic;


public interface SysDicService extends BaseService<SysDic> {

	public List<SysDic> findByGroup();

	public void deleteAndAdd(Serializable id, SysDic sysDic);

	public void updateDicType(String oldType,String newType,String newTypeDesc);
	
}
