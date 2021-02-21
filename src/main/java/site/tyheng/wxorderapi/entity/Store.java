package site.tyheng.wxorderapi.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商店信息
 * @author tangyiheng
 */
@Data
@Builder
@TableName("store_info")
public class Store {
    /**
     * 商店的id
     */
    @TableId(value = "store_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商店名称
     */
    @TableField(value = "store_name")
    private String name;
    /**
     * 商店图片的url
     */
    @TableField(value = "store_image_url")
    private String imageUrl;
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
