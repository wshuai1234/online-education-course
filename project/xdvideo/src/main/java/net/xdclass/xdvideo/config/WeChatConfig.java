package net.xdclass.xdvideo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * wechat configuration class
 */
@Configuration
@PropertySource(value="classpath:application.properties")
public class WeChatConfig {


    /**
     * public account appid
     */
    @Value("${wxpay.appid}")
    private String appId;

    /**
     * public account secret key
     */
    @Value("${wxpay.appsecret}")
    private String appsecret;


    /**
     * open platform appid
     */
    @Value("${wxopen.appid}")
    private String openAppid;

    /**
     * open platform appsecret
     */
    @Value("${wxopen.appsecret}")
    private String openAppsecret;


    /**
     * open platform callback url
     */
    @Value("${wxopen.redirect_url}")
    private String openRedirectUrl;


    /**
     * open platform barcode connection url
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";


    /**
     * open platform get access_token address
     */
    private final static String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    /**
     * retreive user info
     */
    private final static String OPEN_USER_INFO_URL ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";


    /**
     * saler id
     */
    @Value("${wxpay.mer_id}")
    private String mchId;


    /**
     * pay key
     */
    @Value("${wxpay.key}")
    private String key;

    /**
     * wechat pay callback url
     */
    @Value("${wxpay.callback}")
    private String payCallbackUrl;


    /**
     * integration pay url
     */
    private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPayCallbackUrl() {
        return payCallbackUrl;
    }

    public void setPayCallbackUrl(String payCallbackUrl) {
        this.payCallbackUrl = payCallbackUrl;
    }

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
