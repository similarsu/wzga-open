package cn.wzga.open.service.sys.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.sys.SysRoleDao;
import cn.wzga.open.entity.sys.SysRole;
import cn.wzga.open.service.sys.SysRoleService;


@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
	@Resource(name="sysRoleDao")
	private SysRoleDao sysRoleDao;

	@PostConstruct
	public void injectBaseDao() {
		super.injectBaseDao(sysRoleDao);
	}
}
