package cn.wzga.open.service.dev;

import cn.wzga.core.service.base.BaseService;
import cn.wzga.open.entity.dev.Application;

public interface ApplicationService extends BaseService<Application> {

    Application findByAppId(String clientId);

}
