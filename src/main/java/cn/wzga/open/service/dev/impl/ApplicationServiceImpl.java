package cn.wzga.open.service.dev.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.dev.ApplicationDao;
import cn.wzga.open.entity.dev.Application;
import cn.wzga.open.service.dev.ApplicationService;

@Service("applicationService")
@Transactional
public class ApplicationServiceImpl extends BaseServiceImpl<Application>
        implements
            ApplicationService {
    @Resource(name = "applicationDao")
    private ApplicationDao applicationDao;

    @PostConstruct
    public void injectBaseDao() {
        super.injectBaseDao(applicationDao);
    }

    @Override
    public Application findByAppId(String appId) {
        // TODO Auto-generated method stub
        Where where = Where.getInstance().equal("appId", appId);
        return applicationDao.find(where);
    }
}
