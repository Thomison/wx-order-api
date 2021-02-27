package site.tyheng.wxorderapi.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.Coupon;
import site.tyheng.wxorderapi.service.ICouponService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.util.List;

/**
 *
 * @author tangyiheng
 */
@RestController
public class CouponController {

    @Autowired
    public ICouponService couponService;

    /**
     * 查询所有优惠券信息
     */
    @GetMapping("/coupons")
    public CommonResult getAllCoupons() {
        List<Coupon> couponList = couponService.list();
        if (couponList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(couponList, "查询成功");
        }
    }

    /**
     * 添加优惠券
     */
    @PostMapping(value = "/coupon", produces = "application/json")
    public CommonResult addCoupon(@RequestBody JSONObject jsonObject) {
        Coupon coupon = JSONUtil.toBean(jsonObject, Coupon.class);
        boolean hasSave = couponService.save(coupon);
        if (hasSave) {
            return CommonResult.success(null, "创建成功");
        } else {
            return CommonResult.failed("创建失败");
        }
    }

    /**
     * 更新优惠券
     */
    @PutMapping("/coupon")
    public CommonResult updateCoupon(@RequestBody Coupon coupon) {
        boolean hasUpdate = couponService.updateById(coupon);
        if (hasUpdate) {
            return CommonResult.success(null, "更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    /**
     * 删除优惠券
     */
    @DeleteMapping("/coupon/{id}")
    public CommonResult removeCoupon(@PathVariable(value = "id") Integer id) {
        boolean hasRemove = couponService.removeById(id);
        if (hasRemove) {
            return CommonResult.success(null, "删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
}
