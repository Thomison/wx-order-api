package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单 实体类
 * @author tangyiheng
 */
@Data
@Builder
@TableName(value = "order_master")
public class Order {
    /**
     * 主键id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户openid
     */
    @TableField(value = "open_id")
    private String openId;
    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;
    /**
     * 订单总金额
     */
    @TableField(value = "order_amount_total")
    private Integer orderTotalAmount;
    /**
     * 优惠总金额
     */
    @TableField(value = "discount_amount")
    private Integer discountAmount;
    /**
     * 支付总金额
     */
    @TableField(value = "pay_amount_total")
    private Integer payTotalAmount;
    /**
     * 记录创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 记录修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 订单商品列表
     */
    private List<OrderItem> goods;
}
