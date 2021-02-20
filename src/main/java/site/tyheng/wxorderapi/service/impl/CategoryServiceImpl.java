package site.tyheng.wxorderapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Category;
import site.tyheng.wxorderapi.mapper.CategoryMapper;
import site.tyheng.wxorderapi.service.ICategoryService;

/**
 * 商品分类的 service实现类
 * @author tangyiheng
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}
