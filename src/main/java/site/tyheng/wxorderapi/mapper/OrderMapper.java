package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Order;

import java.util.List;

/**
 * @author tangyiheng
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 获取所有订单信息
     * @return List<Order> 订单列表
     */
    List<Order> getOrders();
}
