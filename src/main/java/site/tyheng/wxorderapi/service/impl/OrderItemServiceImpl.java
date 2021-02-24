package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.OrderItem;
import site.tyheng.wxorderapi.mapper.OrderItemMapper;
import site.tyheng.wxorderapi.service.IOrderItemService;

/**
 * 订单商品的 service 实现类
 * @author tangyiheng
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
                                    implements IOrderItemService {
    //
}
