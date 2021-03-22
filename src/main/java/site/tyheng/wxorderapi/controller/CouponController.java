package site.tyheng.wxorderapi.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.Coupon;
import site.tyheng.wxorderapi.entity.CouponUser;
import site.tyheng.wxorderapi.entity.User;
import site.tyheng.wxorderapi.service.*;
import site.tyheng.wxorderapi.utils.CommonResult;
import sun.util.resources.LocaleData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tangyiheng
 */
@RestController
public class CouponController {

    @Autowired
    public ICouponService couponService;

    @Autowired
    public ICouponUserService couponUserService;

    @Autowired
    public IUserService userService;

    @Autowired
    public IStoreService storeService;

    @Autowired
    public IGoodService goodService;

    /**
     * 查询所有优惠券信息
     */
    @GetMapping("/coupons")
    public CommonResult getAllCoupons() {
        List<Coupon> couponList = couponService.list();
        List<JSONObject> result = new ArrayList<>();
        for (Coupon coupon: couponList) {
            JSONObject jsonObject = JSONUtil.parseObj(coupon);
            jsonObject.set("storeName", storeService.getById(coupon.getStoreId()).getName());
            result.add(jsonObject);
        }
        if (couponList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(result, "查询成功");
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

    /**
     * 用户领取优惠券
     */
    @PostMapping("/coupon/get")
    public CommonResult createCouponUser(@RequestBody CouponUser couponUser) {
        // 查询用户
        User user = userService.getOne(
                new QueryWrapper<User>().eq("open_id", couponUser.getUserOpenID())
        );
        if (user == null) {
            return CommonResult.failed("领取失败：用户不存在");
        }
        // 查询并更新优惠券信息
        Coupon coupon = couponService.getById(couponUser.getCouponId());
        if (coupon == null) {
            return CommonResult.failed("领取失败：优惠券不存在");
        }
        if (coupon.getTotal() < 0) {
            return CommonResult.failed("领取失败：优惠券已被领光");
        } else if (coupon.getTotal() == 1) {
            // 设置优惠券领取完毕
            coupon.setTotal(-1);
        } else if (coupon.getTotal() > 1) {
            // 更新优惠券数量
            coupon.setTotal(coupon.getTotal()-1);
        }
        couponService.updateById(coupon);

        // 保存用户领取优惠券信息
        couponUser.setUserId(user.getId());
        // 设置优惠券状态为 未使用
        couponUser.setCouponStatus(0);
        // 设置优惠券的有效时间
        couponUser.setStartTime(LocalDateTime.now());
        couponUser.setEndTime(couponUser.getStartTime().plusSeconds(coupon.getSeconds()));
        couponUserService.save(couponUser);

        return CommonResult.success(null, "领取成功");
    }

    /**
     * 根据 openId 查询用户领取的所有优惠券
     */
    @GetMapping("/coupons/{openId}")
    public CommonResult getAllUserCoupon(@PathVariable String openId) {
        final LocalDateTime checkTime = LocalDateTime.now();
        // 通过openid查找指定用户领取的优惠券
        List<CouponUser> couponUserList =
                couponUserService.list(
                        new QueryWrapper<CouponUser>().eq("open_id", openId));
        // 构造返回结果(优惠券列表)
        List<JSONObject> result = new ArrayList<>();
        for(CouponUser couponUser: couponUserList) {
            // 检查优惠券有效期 更新优惠券状态
            if (checkTime.isAfter(couponUser.getEndTime())) {
                // 设置优惠券已过期
                couponUser.setCouponStatus(2);
                couponUserService.updateById(couponUser);
            }
            JSONObject jsonObject = JSONUtil.parseObj(couponUser);
            // 构造用户领取优惠券的信息
            Coupon coupon = couponService.getById(couponUser.getCouponId());
            jsonObject.set("couponName", coupon.getCouponName());
            jsonObject.set("couponDesc", coupon.getCouponDesc());
            jsonObject.set("couponMin", coupon.getCouponMin());
            jsonObject.set("discount", coupon.getDiscount());
            jsonObject.set("storeId", coupon.getStoreId());
            if (coupon.getStoreId() != 0) {
                jsonObject.set("storeName", storeService.getById(coupon.getStoreId()).getName());
            }
            jsonObject.set("goodId", coupon.getGoodId());
            if (coupon.getGoodId() != 0) {
                jsonObject.set("goodName", goodService.getById(coupon.getGoodId()).getName());
            }
            result.add(jsonObject);
        }

        if (couponUserList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(result, "查询成功");
        }
    }

//    /**
//     * 检查并更新用户优惠券状态
//     */
//    @GetMapping("/coupon/{openId}/check")
//    public CommonResult updateUserCouponsStatus(@PathVariable String openId) {
//        // 通过openid查找指定用户领取的优惠券
//        List<CouponUser> couponUserList =
//                couponUserService.list(
//                        new QueryWrapper<CouponUser>().eq("open_id", openId));
//        // 检查优惠券
//
//    }

}
