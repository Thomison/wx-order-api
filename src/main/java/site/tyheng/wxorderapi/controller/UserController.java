package site.tyheng.wxorderapi.controller;


import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.User;
import site.tyheng.wxorderapi.service.IUserService;
import site.tyheng.wxorderapi.utils.CommonResult;
import site.tyheng.wxorderapi.utils.WeChatUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  处理普通用户登录小程序的请求
 *
 * @author tangyiheng
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    public IUserService userService;

    /**
     * 处理用户登录的post请求
     *
     * @param param
     * @return
     */
    @PostMapping("/user/login")
    public CommonResult login(@RequestBody Map param) {
        String code = (String) param.get("code");
        String rawData = (String) param.get("rawData");
        String signature = (String) param.get("signature");
        String iv = (String) param.get("iv");
        if (code==null || rawData==null || signature==null || iv==null) {
            return CommonResult.failed("登陆失败：缺少必要信息");
        }
        // rawData: 用户非敏感信息  signature: 签名
        JSONObject rawDataJson = JSONUtil.parseObj(rawData);
        // 接受小程序发送的 code，发送本地请求获取 session_key 和 openid(用户唯一认证)
        JSONObject sessionKeyOpenid = WeChatUtil.getSessionKeyOrOpenid(code);
        String openId = sessionKeyOpenid.get("openid", String.class);
        String sessionKey = sessionKeyOpenid.get("session_key", String.class);
        // 校验签名
        String signature2 = DigestUtil.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return CommonResult.validateFailed("签名校验失败");
        }
        // 根据 openid 在数据库中查找用户信息
        User user = userService.getOne(new QueryWrapper<User>().eq("open_id", openId), false);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户首次登陆 构造用户对象
            user = User.builder()
                    .openId(openId)
                    .skey(skey)
                    .sessionKey(sessionKey)
                    .nickName(rawDataJson.get("nickName", String.class))
                    .avatar_url(rawDataJson.get("avatarUrl", String.class))
                    .gender(rawDataJson.get("gender", Integer.class))
                    .country(rawDataJson.get("country", String.class))
                    .province(rawDataJson.get("province", String.class))
                    .city(rawDataJson.get("city", String.class))
                    .confuse(true)
                    .money(100)
                    .lastVisitTime(LocalDateTime.now())
                    .build();
            // 在数据库中保存用户个人信息
            userService.save(user);
        } else {
            // 用户非首次登陆 刷新最后登陆时间
            user.setLastVisitTime(LocalDateTime.now());
            // 重新设置会话 skey
            user.setSkey(skey);
            userService.updateById(user);
        }
        // 构造返回结果
        JSONObject jsonObject = new JSONObject();
        // 会话key
        jsonObject.set("skey", skey);
        // 用户唯一认证id
        jsonObject.set("openid", user.getOpenId());
        return CommonResult.success(jsonObject, "登陆成功");
    }


    /**
     * 查询所有用户信息
     */
    @GetMapping("/users")
    public CommonResult getAllUser() {
        List<User> userList = userService.list();
        if (userList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(userList, "查询成功");
        }
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/user")
    public CommonResult updateUser(@RequestBody User user) {
        boolean hasUpdate = userService.updateById(user);
        if (hasUpdate) {
            return CommonResult.success(null, "更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping("/user/{id}")
    public CommonResult removeUser(@PathVariable(value = "id") Integer id) {
        boolean hasRemove = userService.removeById(id);
        if (hasRemove) {
            return CommonResult.success(null, "删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
}
