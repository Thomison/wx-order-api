package site.tyheng.wxorderapi.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.tyheng.wxorderapi.entity.Order;
import site.tyheng.wxorderapi.entity.OrderItem;
import site.tyheng.wxorderapi.entity.User;
import site.tyheng.wxorderapi.service.IOrderItemService;
import site.tyheng.wxorderapi.service.IOrderService;
import site.tyheng.wxorderapi.service.IUserService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author tangyiheng
 */
@RestController
public class OrderController {

    @Autowired
    public IUserService userService;

    @Autowired
    public IOrderService orderService;

    @Autowired
    public IOrderItemService orderItemService;

    /**
     * 查询所有订单
     */
    @GetMapping("/orders")
    public CommonResult getAllOrders() {
        List<Order> orderList = orderService.listAll();
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
        System.out.println(jsonObject);
        // 将字符串解析成json对象
//        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        // 将json对象解析成order对象
        Order order = JSONUtil.toBean(jsonObject, Order.class);
        System.out.println(order);

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
        // 刷新用户余额信息
        user.setMoney(user.getMoney() - order.getPayTotalAmount());
        userService.updateById(user);
        // 创建订单编号
        String orderNo = UUID.randomUUID().toString();
        order.setOrderNo(orderNo);
        // 保存订单主表
        boolean hasSave = orderService.save(order);
        if (!hasSave) {
            return CommonResult.failed("创建订单失败");
        }
        Order orderSaved = orderService.getOne(
                new QueryWrapper<Order>().eq("order_no", orderNo)
        );
        // 获取订单产品列表
        for (OrderItem item : order.getOrderItems()) {
            item.setOrderId(orderSaved.getId());
            item.setOrderNo(orderSaved.getOrderNo());
            item.setCreateTime(orderSaved.getCreateTime());
            item.setUpdateTime(orderSaved.getUpdateTime());
            // 保存订单从表
            orderItemService.save(item);
        }
        // 返回结果
        return CommonResult.success(null, "创建订单成功");
    }
}
