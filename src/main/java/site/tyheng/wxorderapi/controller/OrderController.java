package site.tyheng.wxorderapi.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.*;
import site.tyheng.wxorderapi.service.*;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author tangyiheng
 */
@RestController
public class OrderController {
    /**
     * 创建订单后的订单状态：待收货-1
     */
    final int INIT_STATUS = 1;

    @Autowired
    public IUserService userService;

    @Autowired
    public IOrderService orderService;

    @Autowired
    public IOrderItemService orderItemService;

    @Autowired
    public IGoodService goodService;

    @Autowired
    public ICategoryService categoryService;

    @Autowired
    public IStoreService storeService;

    @Autowired
    public ICouponService couponService;

    @Autowired
    public ICouponUserService couponUserService;

    /**
     * 查询所有订单
     */
    @GetMapping("/orders")
    public CommonResult getAllOrders() {
        List<Order> orderList = orderService.listAll();
        for(Order order: orderList) {
            // 填充用户信息
            User user = userService.getOne(
                    new QueryWrapper<User>().eq("open_id", order.getOpenId())
            );
            // 用户昵称
            order.setUserName(user.getNickName());
            // 填充商品信息
            for (OrderItem item: order.getOrderItems()) {
                GoodCateStoreVO vo = goodService.getOneById(item.getGoodId());
                // 商品名称
                item.setGoodName(vo.getGoodName());
                // 商品图片
                item.setGoodImageUrl(vo.getGoodImageUrl());
                // 商品所属分类
                item.setCateName(vo.getCateName());
                // 商品所属店铺
                item.setStoreName(vo.getStoreName());
            }
        }
        if (orderList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(orderList, "查询成功");
        }
    }

    /**
     * 查询用户的所有主订单信息
     */
    @GetMapping("/orders/{openid}")
    public CommonResult getUserAllOrders(@PathVariable(value = "openid") String openid) {
        List<Order> orderList = orderService.list(
                new QueryWrapper<Order>().eq("open_id", openid)
        );
        if (orderList == null) {
            return CommonResult.failed("查询失败");
        } else {
            return CommonResult.success(orderList, "查询成功");
        }
    }

    /**
     * 创建订单
     */
    @PostMapping(value = "/order", produces = "application/json")
    public CommonResult createOrder(@RequestBody JSONObject jsonObject) {
        // 解析Json
        String openId = jsonObject.get("openId", String.class);
        OrderItem[] orderItems = (OrderItem[]) jsonObject.getJSONArray("orderItems").toArray();
        Integer[] coupons = (Integer[]) jsonObject.getJSONArray("coupons").toArray();

        // 获取用户id
        if (openId == null || openId.equals("")) {
            return CommonResult.failed("创建订单失败：请先登录");
        }
        // 根据用户id查找用户
        User user = userService.getOne(new QueryWrapper<User>().eq("open_id", openId));
        if (user == null) {
            return CommonResult.failed("创建订单失败：不存在该用户");
        }

        // 订单创建时间
        LocalDateTime orderCreateTime = LocalDateTime.now();
        // 计算订单总价格 并 创建订单子表
        int total = 0;
        List<Integer> goodIdList = new ArrayList<>();
        for (OrderItem item : orderItems) {
            total += item.getGoodPrice() * item.getGoodNum();
            goodIdList.add(item.getGoodId());
        }
        // 计算优惠金额
        int discount = 0;
        for (Integer id : coupons) {
            CouponUser couponUser = couponUserService.getById(id);
            Coupon coupon = couponService.getById(couponUser.getCouponId());
            if (orderCreateTime.isBefore(couponUser.getEndTime())) {
                // 优惠券在有效期内
                if (coupon.getGoodId() == 0 && total >= coupon.getCouponMin()) {
                    // 店铺满减优惠券
                    discount += coupon.getDiscount();
                } else if (coupon.getGoodId() != 0 && goodIdList.contains(coupon.getGoodId())) {
                    // 指定商品优惠券
                    discount += coupon.getDiscount();
                }
            }
        }
        if (user.getMoney() < (total - discount)) {
            return CommonResult.failed("创建订单失败：余额不足");
        }
        // 创建订单
        Order order = Order.builder()
                .openId(openId)
                .userName(user.getNickName())
                .orderNo(UUID.randomUUID().toString())
                .orderStatus(INIT_STATUS)
                .orderTotalAmount(total)
                .discountAmount(discount)
                .payTotalAmount(total - discount)
                .build();
        // 保存订单
        boolean hasSaveOrder = orderService.save(order);
        if (!hasSaveOrder) {
            return CommonResult.failed("创建订单失败");
        }
        // 更新用户账户余额
        user.setMoney(user.getMoney() - order.getPayTotalAmount());
        boolean hasUpdateUser = userService.updateById(user);
        if (!hasUpdateUser) {
            return CommonResult.failed("更新用户账户余额失败");
        }
        // 更新优惠券信息
        for (Integer id : coupons) {
            CouponUser couponUser = couponUserService.getById(id);
            Coupon coupon = couponService.getById(couponUser.getCouponId());
            if (orderCreateTime.isBefore(couponUser.getEndTime())) {
                // 优惠券在有效期内
                if ((coupon.getGoodId() == 0 && total >= coupon.getCouponMin())
                    || (coupon.getGoodId() != 0 && goodIdList.contains(coupon.getGoodId()))) {
                    // 设置优惠券使用时间、状态已使用
                    couponUser.setUsedTime(orderCreateTime);
                    couponUser.setCouponStatus(1);
                }
            } else {
                // 设置优惠券状态已过期
                couponUser.setCouponStatus(2);
            }
            couponUser.setOrderId(order.getId());
            couponUser.setOrderNo(order.getOrderNo());
            couponUserService.updateById(couponUser);
        }
        // 保存订单附表
        for (OrderItem item : orderItems) {
            item.setOrderId(order.getId());
            item.setOrderNo(order.getOrderNo());
            item.setCreateTime(orderCreateTime);
            item.setUpdateTime(orderCreateTime);
            orderItemService.save(item);
            // 更新商品信息
            Good good = goodService.getById(item.getGoodId());
            good.setSaleNum(good.getSaleNum() + item.getGoodNum());
            goodService.updateById(good);
        }
        // 返回订单编号
        return CommonResult.success(order.getOrderNo(), "创建订单成功");
    }

    /**
     * 更新订单
     */
    @PutMapping("/order")
    public CommonResult updateOrder(@RequestBody Order order) {
        boolean hasUpdate = orderService.updateById(order);
        if (hasUpdate) {
            return CommonResult.success(null, "更新订单成功");
        } else {
            return CommonResult.failed("更新订单失败");
        }
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/order/{id}")
    public CommonResult removeOrder(@PathVariable(value = "id") Integer id) {
        boolean hasRemove = orderService.removeById(id);
        if (hasRemove) {
            return CommonResult.success(null, "删除订单成功");
        } else {
            return CommonResult.failed("删除订单失败");
        }
    }
}
