package cn.wzga.open.cache;

import cn.wzga.core.exception.ServiceException;
import cn.wzga.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * <p>
 * Description:系统设置配置文件缓存
 * </p>
 *
 * @author chenjingchai
 * @author cl
 * @version 1.0 2014-10-24
 * @since 2015/12/30
 */
public class SettingCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingCache.class);

    private static String pkiPort = "8443";

    private static String personHeadRemote = "person/head/remote/";// 人员基本信息照片--常口照片存放路径

    private static String personHeadLocal = "person/head/local/";// 人员基本信息照片--本地上传照片存放路径

    private static String personPhoto = "person/photo/";// 人像信息采集正面照，左右侧面照存放路径

    private static String personTraits = "person/traits/"; // 特殊特征照片存放路径

    private static String personInspect = "person/inspect/";// 随身物品盘查照片存放路径

    private static String storageConfURI = "disk://c/piqc";// 图片存储路径

    private static String soaLink = "http://10.119.255.143:8080/soa";// soa访问路径

    private static String loginRedirectMode = "pki";

    private static String regRoleName = "普通用户";

    private static String serverDomain = "localhost";

    private static Integer serverPort = 80;

    /**
     * 初始化
     */
    public static void init() {
        InputStream inputStream = SettingCache.class.getResourceAsStream("/setting.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new ServiceException("error.prop.read");
        }

        pkiPort = prop.getProperty("pki_port");

        personHeadRemote = prop.getProperty("person_head_remote");
        personHeadLocal = prop.getProperty("person_head_local");
        personPhoto = prop.getProperty("person_photo");
        personTraits = prop.getProperty("person_traits");
        personInspect = prop.getProperty("person_inspect");

        storageConfURI = prop.getProperty("storage");

        soaLink = prop.getProperty("soa_link");
        loginRedirectMode = prop.getProperty("app.login.redirect.mode");
        regRoleName = prop.getProperty("app.reg.role.name");

        if (!StringUtil.isBlank(regRoleName)) {
            try {
                regRoleName = new String(regRoleName.getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(null, e);

                }
            }
        }

        serverDomain = prop.getProperty("app.server.domain");
        serverPort = Integer.parseInt(prop.getProperty("app.server.port"));
    }

    public static void stop() {
        pkiPort = null;
        personHeadRemote = null;
        personHeadLocal = null;
        personPhoto = null;
        personTraits = null;
        personInspect = null;
        soaLink = null;
        soaLink = null;
    }

    public static String getPkiPort() {
        return pkiPort;
    }

    /**
     * @return the personHeadRemote
     */
    public static String getPersonHeadRemote() {
        return personHeadRemote;
    }

    /**
     * @return the personHeadLocal
     */
    public static String getPersonHeadLocal() {
        return personHeadLocal;
    }

    /**
     * @return the personPhoto
     */
    public static String getPersonPhoto() {
        return personPhoto;
    }

    /**
     * @return the personTraits
     */
    public static String getPersonTraits() {
        return personTraits;
    }

    /**
     * @return the personInspect
     */
    public static String getPersonInspect() {
        return personInspect;
    }

    public static String getStorageConfURI() {
        return storageConfURI;
    }

    /**
     * @return the soaLink
     */
    public static String getSoaLink() {
        return soaLink;
    }

    public static String getLoginRedirectMode() {
        return loginRedirectMode;
    }

    public static String getRegRoleName() {
        return regRoleName;
    }

    public static String getServerDomain() {
        return serverDomain;
    }

    public static Integer getServerPort() {
        return serverPort;
    }

    /**
     * <p>获取ip:port</p>
     *
     * @return ip:port
     * @author cl
     * @since 2015/01/07
     */
    public static String getHostname() {
        StringBuilder hostname = new StringBuilder(getServerDomain());
        if (80 != getServerPort()) {
            hostname.append(":");
            hostname.append(getServerPort());
        }

        return hostname.toString();

    }
}


