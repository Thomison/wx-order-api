package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单商品 实体类
 * @author tangyiheng
 */
@Data
@Builder
@TableName(value = "order_detail")
public class OrderItem {
    /**
     * 主键id
     */
    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Integer orderId;
    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;
    /**
     * 商品id
     */
    @TableField(value = "good_id")
    private Integer goodId;
    /**
     * 商品数量
     */
    @TableField(value = "good_num")
    private Integer goodNum;
    /**
     * 商品价格
     */
    @TableField(value = "good_price")
    private Integer goodPrice;
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
}
