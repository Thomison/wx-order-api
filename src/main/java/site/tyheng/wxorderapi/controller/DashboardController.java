package site.tyheng.wxorderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.tyheng.wxorderapi.service.IGoodService;
import site.tyheng.wxorderapi.service.IOrderService;
import site.tyheng.wxorderapi.service.IUserService;
import site.tyheng.wxorderapi.utils.CommonResult;

/**
 * 管理后台首页
 *
 * @author tangyiheng
 */
@RestController
public class DashboardController {

    @Autowired
    public IUserService userService;

    @Autowired
    public IGoodService goodService;

    @Autowired
    public IOrderService orderService;

    /**
     * 获取用户数量
     */
    @GetMapping("/user/count")
    public CommonResult getUserCount() {
        int count = userService.count();
        return CommonResult.success(count, "查询成功");
    }

    /**
     * 获取商品数量
     */
    @GetMapping("/good/count")
    public CommonResult getGoodCount() {
        int count = goodService.count();
        return CommonResult.success(count, "查询成功");
    }

    /**
     * 获取订单数量
     */
    @GetMapping("/order/count")
    public CommonResult getOrderCount() {
        int count = orderService.count();
        return CommonResult.success(count, "查询成功");
    }
}
