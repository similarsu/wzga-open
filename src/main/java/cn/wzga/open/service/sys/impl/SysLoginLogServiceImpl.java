package cn.wzga.open.service.sys.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.sys.SysLoginLogDao;
import cn.wzga.open.entity.sys.SysLoginLog;
import cn.wzga.open.service.sys.SysLoginLogService;


@Service("sysLoginLogService")
@Transactional
public class SysLoginLogServiceImpl extends BaseServiceImpl<SysLoginLog> implements SysLoginLogService {
	@Resource(name="sysLoginLogDao")
	private SysLoginLogDao sysLoginLogDao;

	@PostConstruct
	public void injectBaseDao() {
		super.injectBaseDao(sysLoginLogDao);
	}
}
