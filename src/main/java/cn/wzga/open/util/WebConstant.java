package cn.wzga.open.util;

public class WebConstant {

    // 登录状态
    public final static int LOGIN_NORMAL_IN = 1;// 普通登录（帐号密码）
    public final static int LOGIN_OUT = 2;// 退出
    public final static int LOGIN_PKI_IN = 3; // PKI登录
    public final static int LOGIN_REGISTER = 4;// PKI注册

    // 默认管理员
    public final static int USER_ADMIN = 1;
    // 默认超级管理员角色
    public final static int USER_ADMIN_ROLE = 1;
    // 默认分配角色
    public final static int USER_DEFUALT_ROLE = 2;

    // 组织机构相关常量
    public final static String DEPARTMENT_DIC_TYP = "01";// 组织机构 dict type
    public final static int DEPARTMENT_ENABLE_NO = 0;// 启用，否
    public final static int DEPARTMENT_ENABLE_YES = 1;// 启用，是

    public static final String INVALID_CLIENT_DESCRIPTION = "客户端验证失败，如错误的client_id/client_secret。";

    public static String RESOURCE_SERVER_NAME = "chapter17-server";

}
