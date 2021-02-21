package site.tyheng.wxorderapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.tyheng.wxorderapi.entity.Store;
import site.tyheng.wxorderapi.service.IStoreService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.util.List;

/**
 * 店铺信息的 controller
 * @author tangyiheng
 */
@RestController
public class StoreController {

    @Autowired
    public IStoreService storeService;

    /**
     * 获取所有店铺信息
     */
    @GetMapping("/stores")
    public CommonResult getAll() {
        List<Store> storeList = storeService.list();
        if (storeList != null) {
            return CommonResult.success(storeList, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }
}
