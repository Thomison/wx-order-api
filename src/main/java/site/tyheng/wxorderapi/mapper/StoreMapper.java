package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Store;

/**
 * 商店信息的 mapper
 * @author tangyiheng
 */
@Repository
public interface StoreMapper extends BaseMapper<Store> {
}
