package site.tyheng.wxorderapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Category;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 测试 CategoryService
 */
@Slf4j
@SpringBootTest
public class TestCategoryService {

    @Autowired
    public ICategoryService categoryService;

//    @Test
//    public void testInsert() {
//        Category c1 = Category.builder().name("主食").build();
//        Category c2 = Category.builder().name("小食").build();
//        Category c3 = Category.builder().name("饮料").build();
//        Category c4 = Category.builder().name("甜品").build();
//        // 添加单条数据
//        categoryService.save(c1);
//        // 添加批量数据
//        categoryService.saveBatch(Arrays.asList(c2, c3, c4));
//    }

//    @Test
//    public void testSelect() {
//        // 查询所有
//        categoryService.list();
//        // 根据 id 查询
//        categoryService.listByIds(Arrays.asList(1));
//    }

//    @Test
//    public void testUpdate() {
//        Category c = categoryService.getOne(new QueryWrapper<Category>().eq("cate_name", "甜品"));
//        c.setName("甜点");
//        // 根据 id 查询进而更新
//        categoryService.updateById(c);
//    }


}
