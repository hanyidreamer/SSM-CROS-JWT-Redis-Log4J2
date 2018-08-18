package com.demo.common;

/**
 * 常量
 *
 * @author tuzhengsong
 */
@SuppressWarnings("ALL")
public class Constants {
    /** -------------------约定的基本参数------------------------ */
    /**
     * ajax请求返回标识符 - 000000成功
     */
    public static final String AJAX_SUCCESS = "000000";
    /**
     * ajax请求返回标识符 - 000001失败
     */
    public static final String AJAX_FAIL = "000001";
    /**
     * ajax请求返回标识符 - 000002警告（允许通过请求）
     */
    public static final String AJAX_WARN = "000002";
    /**
     * ajax请求返回标识符 - 000003没权限
     */
    public static final String AJAX_NO_AUTH = "000003";
    /**
     * ajax请求返回标识符 - 000004重定向
     */
    public static final String AJAX_REDIRECT = "000004";
    /**
     * 文件上传目录 eg:uploadFiles/
     */
//    public static final String UPLOAD_PATH = Global.getConfig("sys.uploadFile");
    /**
     * web路径 eg: http://localhost:8080/
     */
//    public static final String WEB_PATH = Global.getConfig("sys.webPath");

    /**
     * 无权限
     */
    public static final String NO_AUTH = "对不起，您没有相应权限";


    /**
     * 用户是超级管理员
     */
    public static final Byte USER_SUPER_Y = 1;

    /**
     * 当前会话的用户
     */
    public static final String LOGIN_USER = "loginUser";

    /**
     * 当前会话的菜单
     */
    public static final String LOGIN_MENU = "loginMenu";

    /**
     * 请确认信息输入完整
     */
    public static final String NOT_BLANK = "请确认信息输入完整";

    /**
     * 匹配用户信息错误
     */
    public static final String USER_ERROR = "用户名或密码错误";

    /**
     * 返回标准json的content-type
     */
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";


    /**
     * 返回发票原件的静态文件夹地址
     */
    public static final String ORIGINALS_PATH = Global.getConfig("originals");

    /**
     * 返回报销单的静态文件夹地址
     */
    public static final String PNG_FORMS_PATH = Global.getConfig("pngforms");

    /**
     * 返回报销单模板文件的绝对地址
     */
    public static final String FORM_TEM_PATH = Global.getConfig("formtem");

    /**
     * 三十分钟
     */
    public static final Integer MYTHIRTY_MIN = Integer.valueOf(Global.getConfig("thirtymin"));

    public static final Integer MYTEN_MIN = Integer.valueOf(Global.getConfig("tenmin"));


    /**
     * token密钥
     */
    public static final byte[] SECRET = Global.getConfig("secert").getBytes();


    /**
     * 报销单文件夹索引
     */
    public static final String FORMINDEX = Global.getConfig("formindex");


    /**
     * 发票原件
     */
    public static final String ORIINDEX = Global.getConfig("oriindex");

    /**
     * 域名
     */
    public static final String DOMAINNAME = Global.getConfig("domainname");

    public static final String SVSHOST = Global.getConfig("svshost");
    public static final String SVSPORT = Global.getConfig("svsport");


}
