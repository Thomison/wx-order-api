package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Category;

/**
 *  商品分类的 dao
 * @author tangyiheng
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
