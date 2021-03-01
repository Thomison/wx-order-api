package site.tyheng.wxorderapi.controller;


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
            // 填充用户昵称
            User user = userService.getOne(
                    new QueryWrapper<User>().eq("open_id", order.getOpenId())
            );
            order.setUserName(user.getNickName());
            // 若使用了优惠券 则填充优惠券信息
            if (order.getCouponUserId() != null) {
                CouponUser couponUser = couponUserService.getById(order.getCouponUserId());
                Coupon coupon = couponService.getById(couponUser.getCouponId());
                order.setCouponName(coupon.getCouponName());
            }
            // 填充商品信息
            for (OrderItem item: order.getOrderItems()) {
                // 填充商品名称
                GoodCateStoreVO vo = goodService.getOneById(item.getGoodId());
                item.setGoodName(vo.getGoodName());
                // 填充商品所属分类
                item.setCateName(vo.getCateName());
                // 填充商品所属店铺
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
     * 创建订单
     */
    @PostMapping(value = "/order", produces = "application/json")
    public CommonResult createOrder(@RequestBody JSONObject jsonObject) {
        // 解析
        Order order = JSONUtil.toBean(jsonObject, Order.class);
        // 获取用户id
        if (order.getOpenId() == null) {
            return CommonResult.failed("创建订单失败：请先登录");
        }
        // 根据用户id查找用户
        User user = userService.getOne(
                new QueryWrapper<User>().eq("open_id", order.getOpenId())
        );
        if (user == null) {
            return CommonResult.failed("创建订单失败：不存在该用户");
        }
        // 判断用户余额是否足以支付订单
        if (user.getMoney() < order.getPayTotalAmount()) {
            return CommonResult.failed("创建订单失败：余额不足");
        }
        // 判断商品库存是否足以承担订单
        for (OrderItem item : order.getOrderItems()) {
            // 从数据库查询商品库存
            Good good = goodService.getById(item.getGoodId());
            if (good.getStockNum() < item.getGoodNum()) {
                return CommonResult.failed("创建订单失败：库存不足");
            }
        }
        // 刷新用户余额信息
        user.setMoney(user.getMoney() - order.getPayTotalAmount());
        // 更新用户信息回数据库
        userService.updateById(user);

        // 创建订单编号
        String orderNo = UUID.randomUUID().toString();
        order.setOrderNo(orderNo);
        // 设置订单状态为 待收货
        order.setOrderStatus(1);
        // 持久化订单主表到数据库
        boolean hasSave = orderService.save(order);
        if (!hasSave) {
            return CommonResult.failed("创建订单失败");
        }

        // 根据订单编号查询订单主表
        Order orderSaved = orderService.getOne(
                new QueryWrapper<Order>().eq("order_no", orderNo)
        );

        // 查询本次订单中用户使用的优惠券信息
        CouponUser couponUser = couponUserService.getById(order.getCouponUserId());
        // 设置订单信息
        couponUser.setOrderId(orderSaved.getId());
        couponUser.setOrderNo(orderNo);
        // 设置优惠券状态为已使用
        couponUser.setCouponStatus(1);
        // 设置优惠券使用时间
        couponUser.setUsedTime(LocalDateTime.now());
        // 更新用户优惠券信息
        couponUserService.updateById(couponUser);

        // 获取订单产品列表
        for (OrderItem item : order.getOrderItems()) {
            // 持久化订单从表到数据库
            item.setOrderId(orderSaved.getId());
            item.setOrderNo(orderSaved.getOrderNo());
            item.setCreateTime(orderSaved.getCreateTime());
            item.setUpdateTime(orderSaved.getUpdateTime());
            orderItemService.save(item);

            // 刷新商品信息(销量,库存)回数据库
            Good good = goodService.getById(item.getGoodId());
            good.setSaleNum(good.getSaleNum() + item.getGoodNum());
            good.setStockNum(good.getStockNum() - item.getGoodNum());
            goodService.updateById(good);
        }
        // 返回订单编号
        return CommonResult.success(orderNo, "创建订单成功");
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
