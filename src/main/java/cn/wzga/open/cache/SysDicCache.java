package cn.wzga.open.cache;

import cn.wzga.open.entity.sys.SysDic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.*;

/**
 * <p>
 * Description:数据字典缓存
 * </p>
 *
 * @author sutong
 * @version 1.0 2014-07-11
 */
@Component("sysDicCache")
public class SysDicCache {
    private static final Logger logger = LoggerFactory.getLogger(SysDicCache.class);

    @Autowired
    private SessionFactory sessionFactory;

    private static Map<String, Map<String, String>> _CACHE = null;

    private static List<SysDic> _CACHE_LIST = null;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        if (_CACHE != null) {
            return;
        }
        try {
            _CACHE = new HashMap<String, Map<String, String>>();

            Session session = sessionFactory.openSession();
            Query query =
                    session.createQuery("select sysDic from SysDic sysDic where 1=1 order by  sysDic.dicType asc , sysDic.sort asc");
            _CACHE_LIST = query.list();
            session.close();

            String dicType = "";
            Map<String, String> keyValueMap = null;
            for (SysDic sysDic : _CACHE_LIST) {
                if (!sysDic.getDicType().equals(dicType)) {
                    if (!dicType.equals("")) {
                        _CACHE.put(dicType, keyValueMap);
                    }
                    dicType = sysDic.getDicType();
                    keyValueMap = new LinkedHashMap<String, String>();
                }
                keyValueMap.put(sysDic.getDicKey(), sysDic.getDicValue());
            }

            if (!dicType.equals("")) {
                _CACHE.put(dicType, keyValueMap);
            }
        } catch (Exception e) {
            logger.error("init sysDic: {}", e);
        }
    }

    public static void stop() {
        _CACHE = null;
        _CACHE_LIST = null;
    }

    public static String get(String type, String key) {
        if (_CACHE == null) {
            return key;
        }
        Map<String, String> typeKeyMap = (Map<String, String>) _CACHE.get(type);
        if (typeKeyMap == null) {
            return null;
        }

        return typeKeyMap.get(key);
    }

    public static Map<String, String> get(String type) {
        if (_CACHE == null) {
            return null;
        }

        return _CACHE.get(type);
    }

    public static Map<String, Map<String, String>> get() {
        return _CACHE;
    }

    public static List<SysDic> getFromList(String type) {
        if (_CACHE_LIST == null) {
            return null;
        }
        List<SysDic> sysDicList = new ArrayList<SysDic>();
        for (SysDic sysDic : _CACHE_LIST) {
            if (sysDic.getDicType().equals(type)) {
                sysDicList.add(sysDic);
            }
        }

        return sysDicList;
    }

    public static List<SysDic> getFromList() {
        return _CACHE_LIST;
    }
}
