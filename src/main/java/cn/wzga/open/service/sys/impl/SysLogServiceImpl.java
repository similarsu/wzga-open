package cn.wzga.open.service.sys.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.sys.SysLogDao;
import cn.wzga.open.entity.sys.SysLog;
import cn.wzga.open.service.sys.SysLogService;


@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
	@Resource(name="sysLogDao")
	private SysLogDao sysLogDao;

	@PostConstruct
	public void injectBaseDao() {
		super.injectBaseDao(sysLogDao);
	}
}
