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
    @TableField(value = "name")
    private String name;
    /**
     * 优惠券介绍 显示优惠券的使用限制
     */
    @TableField(value = "desc")
    private String desc;
    /**
     * 优惠券数量 如果为0 表示不限量领取
     */
    @TableField(value = "total")
    private int total;
    /**
     * 最少消费多少金额才能使用优惠券
     */
    @TableField(value = "min")
    private int min;
    /**
     * 优惠金额
     */
    @TableField(value = "discount")
    private int discount;
    /**
     * 用户领取优惠券限制 0表示不限量 1表示限领一张
     */
    @TableField(value = "limit")
    private int limit;
    /**
     * 优惠券状态 0表示正常使用 1表示已过期 2表示下架
     */
    @TableField(value = "status")
    private int status;
    /**
     * 商品限制类型 0表示全商品 1表示类目限制 2表示商品限制
     */
    @TableField(value = "goods_type")
    private int goodsType;
    /**
     * 有限时间限制 0表示基于领取时间的有效天数 1表示从start_time到end_time的有效天数
     */
    @TableField(value = "time_type")
    private int timeType;
    /**
     * 基于领取时间的有效天数days
     */
    @TableField(value = "days")
    private int days;
    /**
     * 使用开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;
    /**
     * 使用截止时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

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
