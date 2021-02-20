package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品分类
 * @author tangyiheng
 */
@Data
@Builder
@TableName(value = "goods_category")
public class Category {
    /**
     * 分类id
     */
    @TableId(value = "cate_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    @TableField(value = "cate_name")
    private String name;
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
