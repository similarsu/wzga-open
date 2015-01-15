package cn.wzga.open.service.dev.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.service.base.impl.BaseServiceImpl;
import cn.wzga.open.dao.dev.ServiceDao;
import cn.wzga.open.entity.dev.Service;
import cn.wzga.open.service.dev.ServiceService;

@org.springframework.stereotype.Service("serviceService")
@Transactional
public class ServiceServiceImpl extends BaseServiceImpl<Service> implements ServiceService {
    @Resource(name = "serviceDao")
    private ServiceDao serviceDao;

    @PostConstruct
    public void injectBaseDao() {
        super.injectBaseDao(serviceDao);
    }
}
