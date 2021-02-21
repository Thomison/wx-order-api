package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;
import site.tyheng.wxorderapi.mapper.GoodMapper;
import site.tyheng.wxorderapi.service.IGoodService;

import java.util.List;


/**
 * 商品信息的 service实现类
 * @author tangyiheng
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

    @Override
    public List<GoodCateStoreVO> listAll() {
        return this.baseMapper.selectAll();
    }

    @Override
    public List<GoodCateStoreVO> listByStoreId(Integer storeId) {
        return this.baseMapper.selectAllByStoreId(storeId);
    }

    @Override
    public List<GoodCateStoreVO> listByCateId(Integer cateId) {
        return this.baseMapper.selectAllByCateId(cateId);
    }

    @Override
    public List<GoodCateStoreVO> listByStoreCateId(Integer storeId, Integer cateId) {
        return this.baseMapper.selectAllByStoreCateId(storeId, cateId);
    }

    @Override
    public GoodCateStoreVO getOneById(Integer id) {
        return this.baseMapper.selectById(id);
    }


}
