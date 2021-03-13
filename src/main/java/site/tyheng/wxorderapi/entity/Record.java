package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户对商品的浏览记录
 *
 * @author tangyiheng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "record_info")
public class Record {
    /**
     * 访问记录id - 主键
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户openid
     */
    @TableField(value = "user_openid")
    private String userOpenId;
    /**
     * 店铺id
     */
    @TableField(value = "store_id")
    private Integer storeId;
    /**
     * 商品id
     */
    @TableField(value = "good_id")
    private Integer goodId;
    /**
     * 访问开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;
    /**
     * 访问结束时间
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
