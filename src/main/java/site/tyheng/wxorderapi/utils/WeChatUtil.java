package site.tyheng.wxorderapi.utils;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * 微信小程序工具类
 * @author tangyiheng
 */
public class WeChatUtil {

    /**
     * 获取微信小程序登录授权的 session_key 和 openid
     * @param code
     * @return JSONObject
     */
    public static JSONObject getSessionKeyOrOpenid(String code) {
        // 请求的 url 地址
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        // 设置请求参数
        HashMap<String, Object> requestUrlParams = new HashMap<>();
        requestUrlParams.put("appid", "wxd1f8fd92dd6026d8");
        requestUrlParams.put("secret", "533666220e5b63725c0ade0442c3cab3");
        requestUrlParams.put("js_code", code);
        requestUrlParams.put("grant_type", "authorization_code");
        // 发送请求获取 openid
        String result = HttpUtil.get(requestUrl, requestUrlParams);
        // 将返回结果 解析成 json 格式
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject;
    }


}
