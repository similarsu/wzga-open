package cn.wzga.open.service.secure.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wzga.core.dao.base.support.Where;
import cn.wzga.open.dao.dev.ApplicationDao;
import cn.wzga.open.service.secure.OAuthService;

@Service("oAuthService")
@Transactional
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;

    @Resource(name = "applicationDao")
    private ApplicationDao applicationDao;

    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    @Override
    public void addAuthCode(String authCode, String username) {
        cache.put(authCode, username);
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return (String) cache.get(authCode).get();
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return (String) cache.get(accessToken).get();
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public boolean checkClientId(String clientId) {
        Where where = Where.getInstance().equal("appId", clientId);
        return applicationDao.find(where) != null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        Where where = Where.getInstance().equal("appSecret", clientSecret);
        return applicationDao.find(where) != null;
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }
}
