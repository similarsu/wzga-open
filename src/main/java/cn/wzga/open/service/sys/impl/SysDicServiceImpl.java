package cn.wzga.open.service.sys.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.sys.SysDicDao;
import cn.wzga.open.entity.sys.SysDic;
import cn.wzga.open.service.sys.SysDicService;


@Service("sysDicService")
@Transactional
public class SysDicServiceImpl extends BaseServiceImpl<SysDic> implements SysDicService {
	@Resource(name="sysDicDao")
	private SysDicDao sysDicDao;

	@PostConstruct
	public void injectBaseDao() {
		super.injectBaseDao(sysDicDao);
	}

	@Override
	public List<SysDic> findByGroup() {
		return sysDicDao.findByGroup();
	}

	@Override
	public void deleteAndAdd(Serializable id, SysDic sysDic) {
		sysDicDao.deleteById(id);
		sysDicDao.save(sysDic);
	}

	@Override
	public void updateDicType(String oldType, String newType, String newTypeDesc) {
		// TODO Auto-generated method stub
		sysDicDao.updateDicType(oldType,newType,newTypeDesc);
	}

}
