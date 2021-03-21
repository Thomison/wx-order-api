package site.tyheng.wxorderapi.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户优惠券领取和使用的记录
 *
 * @author tangyiheng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "coupon_user")
public class CouponUser {
    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;
    /**
     * 用户openid
     */
    @TableField(value = "open_id")
    private String userOpenID;
    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    private Integer couponId;
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
     * 使用状态 0表示未使用 1表示已使用 2表示已过期
     */
    @TableField(value = "coupon_status")
    private Integer couponStatus;
    /**
     * 使用时间
     */
    @TableField(value = "used_time")
    private LocalDateTime usedTime;
    /**
     * 有效期开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;
    /**
     * 有效期截止时间
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
