package site.tyheng.wxorderapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.tyheng.wxorderapi.entity.Order;

import java.util.List;


/**
 * 订单 service 接口
 *
 * @author tangyiheng
 */
public interface IOrderService extends IService<Order> {
    /**
     * 创建订单
     *
     * @param order Order 订单信息
     * @return boolean 订单是否创建成功
     */
    boolean create(Order order);
    /**
     * 查询所有订单
     *
     * @return List<Order> 查询全部订单列表
     */
    List<Order> listAll();
}
