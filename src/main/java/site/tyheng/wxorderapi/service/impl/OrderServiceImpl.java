package site.tyheng.wxorderapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Order;
import site.tyheng.wxorderapi.mapper.OrderMapper;
import site.tyheng.wxorderapi.service.IOrderService;

import java.util.List;

/**
 * 订单 service 实现类
 *
 * @author tangyiheng
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
                                implements IOrderService {

    @Override
    public boolean create(Order order) {

        return false;
    }

    @Override
    public List<Order> listAll() {
        List<Order> orderList = this.baseMapper.getOrders();
        return orderList;
    }
}
