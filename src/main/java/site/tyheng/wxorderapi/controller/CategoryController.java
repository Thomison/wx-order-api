package site.tyheng.wxorderapi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.Category;
import site.tyheng.wxorderapi.service.ICategoryService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.util.List;

/**
 * 商品分类的 controller
 * @author tangyiheng
 */
@RestController
public class CategoryController {

    @Autowired
    public ICategoryService categoryService;

    /**
     * 返回所有分类
     */
    @GetMapping("/category/all")
    public CommonResult getAll() {
        List<Category> categoryList = categoryService.list();
        return CommonResult.success(categoryList, "查询成功");
    }

    /**
     * 创建分类
     */
    @PostMapping("/category")
    public CommonResult add(@RequestBody Category category) {
        // 先查询分类是否已存在
        Category one = categoryService.getOne(
                new QueryWrapper<Category>().eq("cate_name", category.getName()));
        if (one != null) {
            // 分类已存在
            return CommonResult.failed("创建失败：分类已存在");
        }
        boolean hasSave = categoryService.save(category);
        if (hasSave) {
            return CommonResult.success(null, "创建成功");
        } else {
            return CommonResult.failed("创建失败");
        }
    }

    /**
     * 更新分类
     */
    @PutMapping("/category")
    public CommonResult update(@RequestBody Category category) {
        boolean hasUpdate = categoryService.updateById(category);
        if (hasUpdate) {
            return CommonResult.success(null, "更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/category/{id}")
    public CommonResult remove(@PathVariable(value = "id") Integer id) {
        boolean hasRemove = categoryService.removeById(id);
        if (hasRemove) {
            return CommonResult.success(null, "删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
}
