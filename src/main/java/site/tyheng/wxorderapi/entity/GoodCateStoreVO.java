package site.tyheng.wxorderapi.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品-分类-商店 联表查询结果
 * @author tangyiheng
 */
@Data
@Builder
public class GoodCateStoreVO {
    private Integer goodId;
    private String goodName;
    private String goodImageUrl;
    private Integer goodPrice;
    private Integer goodSaleNum;
    private Integer cateId;
    private String cateName;
    private Integer storeId;
    private String storeName;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;
}
