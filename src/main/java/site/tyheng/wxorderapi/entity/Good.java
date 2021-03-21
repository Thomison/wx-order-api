package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品信息
 * @author tangyiheng
 */
@Data
@Builder
@TableName(value = "goods_info")
public class Good {
    /**
     * 商品id
     */
    @TableId(value = "good_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品名称
     */
    @TableField(value = "good_name")
    private String name;
    /**
     * 商品图片url
     */
    @TableField(value = "good_image_url")
    private String imageUrl;
    /**
     * 商品当前价格
     */
    @TableField(value = "good_price")
    private Integer price;
    /**
     * 商品销量
     */
    @TableField(value = "good_sale_num")
    private Integer saleNum;
    /**
     * 商品所属分类的id
     */
    @TableField(value = "cate_id")
    private Integer cateId;
    /**
     * 商品所属店铺的id
     */
    @TableField(value = "store_id")
    private Integer storeId;
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
