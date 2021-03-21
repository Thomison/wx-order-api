package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 优惠券基本信息及使用规则
 *
 * @author tangyiheng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "coupon_info")
public class Coupon {

    /**
     * 优惠券id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券名称
     */
    @TableField(value = "coupon_name")
    private String couponName;

    /**
     * 优惠券介绍 显示优惠券的使用限制
     */
    @TableField(value = "coupon_desc")
    private String couponDesc;

    /**
     * 店铺id --- 店铺内使用满减优惠券
     */
    @TableField(value = "store_id")
    private Integer storeId;

    /**
     * 商品id --- 特定商品的限时优惠券
     */
    @TableField(value = "good_id")
    private Integer goodId;

    /**
     * t 如果为0 表示不限量领取
     */
    @TableField(value = "total")
    private Integer total;

    /**
     * 最少消费多少金额才能使用优惠券
     */
    @TableField(value = "coupon_min")
    private Integer couponMin;

    /**
     * 优惠金额
     */
    @TableField(value = "discount")
    private Integer discount;

    /**
     * 用户领取优惠券限制 0表示不限量 1表示限领一张
     */
    @TableField(value = "coupon_limit")
    private Integer couponLimit;

//    /**
//     * 优惠券状态 0表示正常使用 1表示已过期 2表示下架
//     */
//    @TableField(value = "coupon_status")
//    private Integer couponStatus;

    /**
     * 领取后的有效时间 秒为单位
     */
    @TableField(value = "seconds")
    private Long seconds;

    /*********************************************************/

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
