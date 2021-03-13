package site.tyheng.wxorderapi.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.Record;
import site.tyheng.wxorderapi.entity.Store;
import site.tyheng.wxorderapi.entity.User;
import site.tyheng.wxorderapi.service.IGoodService;
import site.tyheng.wxorderapi.service.IRecordService;
import site.tyheng.wxorderapi.service.IStoreService;
import site.tyheng.wxorderapi.service.IUserService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.net.CookieManager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理用户访问记录的 controller
 *
 * @author tangyiheng
 */
@RestController
public class RecordController {

    @Autowired
    public IRecordService recordService;

    @Autowired
    public IUserService userService;

    @Autowired
    public IStoreService storeService;

    @Autowired
    public IGoodService goodService;

    /**
     * 创建访问记录
     */
    @PostMapping(value = "/record", produces = "application/json")
    public CommonResult createRecord(@RequestBody JSONObject jsonObject) {
        // 获取用户 openid 和 商品 id
        String userOpenId = jsonObject.get("userOpenId", String.class);
        Integer goodId = jsonObject.get("goodId", Integer.class);
        if (userOpenId == null) {
            return CommonResult.failed("用户不存在，无法创建访问记录");
        }
        if (goodId == null) {
            return CommonResult.failed("目标商品不存在，无法创建访问记录");
        }
        // 获取店铺 id
        Good good = goodService.getById(goodId);
        if (good == null) {
            return CommonResult.failed("目标商品不存在，无法创建访问记录");
        }
        Integer storeId = good.getStoreId();
        // 构造访问记录
        Record record = Record.builder()
                .userOpenId(userOpenId)
                .storeId(storeId)
                .goodId(goodId)
                .startTime(LocalDateTime.now())
                .build();
        // 保存访问记录
        boolean save = recordService.save(record);
        if (save) {
            return CommonResult.success(record, "创建访问记录成功");
        } else {
            return CommonResult.failed("创建访问记录失败");
        }
    }

    /**
     * 更新访问记录 - 访问结束时间
     */
    @PutMapping(value = "/record/{id}")
    public CommonResult updateRecord(@PathVariable String id) {
        Record record = recordService.getById(id);
        if (record == null) {
            return CommonResult.failed("找不到目标访问记录");
        }
        record.setEndTime(LocalDateTime.now());
        boolean update = recordService.updateById(record);
        if (update) {
            return CommonResult.success(record, "更新访问记录成功");
        } else {
            return CommonResult.failed("更新访问记录失败");
        }
    }

    /**
     * 查询所有访问记录
     */
    @GetMapping(value = "/records")
    public CommonResult getAllRecord() {
        List<Record> recordList = recordService.list();
        if (recordList == null) {
            return CommonResult.failed("查询所有访问记录失败");
        }
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Record record : recordList) {
            JSONObject jsonObject = JSONUtil.parseObj(record);

            // 添加用户信息
            User user = userService.getOne(
                    new QueryWrapper<User>().eq("open_id", record.getUserOpenId()));
            jsonObject.set("userNickName", user.getNickName());
            jsonObject.set("userAvatarUrl", user.getAvatar_url());
            // 添加店铺信息
            Store store = storeService.getById(record.getStoreId());
            jsonObject.set("storeName", store.getName());
            // 添加商品信息
            Good good = goodService.getById(record.getGoodId());
            jsonObject.set("goodName", good.getName());
            jsonObject.set("goodImageUrl", good.getImageUrl());
            // 计算访问持续时间
            Duration duration = Duration.between(record.getStartTime(), record.getEndTime());
            long seconds = duration.getSeconds();
            jsonObject.set("duration", seconds);

            jsonObjectList.add(jsonObject);
        }
        return CommonResult.success(jsonObjectList, "查询所有访问记录成功");
    }
}

