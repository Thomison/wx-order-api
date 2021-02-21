package site.tyheng.wxorderapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;

import java.util.List;


/**
 * 商品信息的 service接口
 * @author tangyiheng
 */
public interface IGoodService extends IService<Good> {
    /**
     * 查询所有商品信息
     *
     * @return List<GoodCateStoreVO>
     */
    List<GoodCateStoreVO> listAll();

    /**
     * 查询指定商店下所有商品信息
     *
     * @param storeId Integer storeId
     * @return List<GoodCateStoreVO>
     */
    List<GoodCateStoreVO> listByStoreId(Integer storeId);

    /**
     * 查询指定分类下所有商品信息
     *
     * @param cateId Integer cateId
     * @return List<GoodCateStoreVO>
     */
    List<GoodCateStoreVO> listByCateId(Integer cateId);

    /**
     * 查询指定店铺和分类下所有商品信息
     *
     * @param storeId Integer storeId
     * @param cateId Integer cateId
     * @return List<GoodCateStoreVO>
     */
    List<GoodCateStoreVO> listByStoreCateId(Integer storeId, Integer cateId);

    /**
     * 查询单个商品信息
     *
     * @param id Integer id
     * @return GoodCateStoreVO
     */
    GoodCateStoreVO  getOneById(Integer id);
}
