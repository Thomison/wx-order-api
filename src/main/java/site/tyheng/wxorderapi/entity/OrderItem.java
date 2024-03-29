package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 订单商品 实体类
 * @author tangyiheng
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
     * 商品名称
     */
    @TableField(exist = false)
    private String goodName;
    /**
     * 商品图片
     */
    @TableField(exist = false)
    private String goodImageUrl;
    /**
     * 商品所属分类
     */
    @TableField(exist = false)
    private Integer cateId;
    @TableField(exist = false)
    private String cateName;
    /**
     * 商品所属店铺
     */
    @TableField(exist = false)
    private Integer storeId;
    @TableField(exist = false)
    private String storeName;
    /**
     * 商品数量
     */
    @TableField(value = "good_num")
    private int goodNum;
    /**
     * 商品价格
     */
    @TableField(value = "good_price")
    private int goodPrice;
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
