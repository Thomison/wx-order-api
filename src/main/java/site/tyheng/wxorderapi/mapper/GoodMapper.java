package site.tyheng.wxorderapi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;

import java.util.List;

/**
 * 商品信息的 mapper
 * @author tangyiheng
 */
@Repository
public interface GoodMapper extends BaseMapper<Good> {

    /**
     * 查询所有商品信息
     *
     * @return List<GoodCateStoreVO>
     */
    @Select("select " +
                "a.good_id, " +
                "a.good_name, " +
                "a.good_image_url, " +
                "a.good_describe, " +
                "a.good_old_price, " +
                "a.good_new_price, " +
                "a.good_sale_num, " +
                "a.good_stock, " +
                "b.cate_id, " +
                "b.cate_name, " +
                "c.store_id, " +
                "c.store_name, " +
                "a.create_time, " +
                "a.update_time " +
            "from " +
                "goods_info as a, goods_category as b, store_info as c " +
            "where " +
                "a.cate_id = b.cate_id and a.store_id = c.store_id ")
    List<GoodCateStoreVO> selectAll();

    /**
     * 通过店铺id 查询指定店铺下所有商品信息
     *
     * @param storeId store id
     * @return List<GoodCateStoreVO>
     */
    @Select("select " +
                "a.good_id, " +
                "a.good_name, " +
                "a.good_image_url, " +
                "a.good_describe, " +
                "a.good_old_price, " +
                "a.good_new_price, " +
                "a.good_sale_num, " +
                "a.good_stock, " +
                "b.cate_id, " +
                "b.cate_name, " +
                "c.store_id, " +
                "c.store_name, " +
                "a.create_time, " +
                "a.update_time " +
            "from " +
                "goods_info as a, goods_category as b, store_info as c " +
            "where " +
                "a.cate_id = b.cate_id and a.store_id = c.store_id and c.store_id = #{storeId}")
    List<GoodCateStoreVO> selectAllByStoreId(@Param("storeId") Integer storeId);

    /**
     * 通过分类id 查询指定分类下所有商品信息
     * @param cateId category id
     * @return List<GoodCateStoreVO>
     */
    @Select("select " +
                "a.good_id, " +
                "a.good_name, " +
                "a.good_image_url, " +
                "a.good_describe, " +
                "a.good_old_price, " +
                "a.good_new_price, " +
                "a.good_sale_num, " +
                "a.good_stock, " +
                "b.cate_id, " +
                "b.cate_name, " +
                "c.store_id, " +
                "c.store_name, " +
                "a.create_time, " +
                "a.update_time " +
            "from " +
                "goods_info as a, goods_category as b, store_info as c " +
            "where " +
                "a.cate_id = b.cate_id and a.store_id = c.store_id and b.cate_id = #{cateId}")
    List<GoodCateStoreVO> selectAllByCateId(@Param("cateId") Integer cateId);

    /**
     * 通过店铺id和分类 查询指定店铺指定分类下所有商品信息
     *
     * @param storeId store id
     * @param cateId store id
     * @return List<GoodCateStoreVO>
     */
    @Select("select " +
                "a.good_id, " +
                "a.good_name, " +
                "a.good_image_url, " +
                "a.good_describe, " +
                "a.good_old_price, " +
                "a.good_new_price, " +
                "a.good_sale_num, " +
                "a.good_stock, " +
                "b.cate_id, " +
                "b.cate_name, " +
                "c.store_id, " +
                "c.store_name, " +
                "a.create_time, " +
                "a.update_time " +
            "from " +
                "goods_info as a, goods_category as b, store_info as c " +
            "where " +
                "a.cate_id = b.cate_id and a.store_id = c.store_id and c.store_id = #{storeId} and b.cate_id = #{cateId}")
    List<GoodCateStoreVO> selectAllByStoreCateId(@Param("storeId") Integer storeId,
                                                 @Param("cateId") Integer cateId);

    /**
     * 根据商品id 查询单条商品信息
     *
     * @param id good id
     * @return GoodCateStoreVO
     */
    @Select("select " +
                "a.good_id, " +
                "a.good_name, " +
                "a.good_image_url, " +
                "a.good_describe, " +
                "a.good_old_price, " +
                "a.good_new_price, " +
                "a.good_sale_num, " +
                "a.good_stock, " +
                "b.cate_id, " +
                "b.cate_name, " +
                "c.store_id, " +
                "c.store_name, " +
                "a.create_time, " +
                "a.update_time " +
            "from " +
                "goods_info as a, goods_category as b, store_info as c " +
            "where " +
                "a.cate_id = b.cate_id and a.store_id = c.store_id and a.good_id = #{id}")
    GoodCateStoreVO selectById(@Param("id") Integer id);
}
