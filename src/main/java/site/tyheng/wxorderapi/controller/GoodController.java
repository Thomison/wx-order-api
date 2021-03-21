package site.tyheng.wxorderapi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.tyheng.wxorderapi.entity.Category;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;
import site.tyheng.wxorderapi.service.ICategoryService;
import site.tyheng.wxorderapi.service.IGoodService;
import site.tyheng.wxorderapi.service.IStoreService;
import site.tyheng.wxorderapi.utils.CommonResult;

import java.util.List;
import java.util.Map;

/**
 * 商品信息的 controller
 * @author tangyiheng
 */
@RestController
public class GoodController {

    @Autowired
    public IGoodService goodService;

    /**
     * 获取所有商品信息
     */
    @GetMapping("/goods")
    public CommonResult getAll() {
        List<GoodCateStoreVO> voList = goodService.listAll();
        if (voList != null) {
            return CommonResult.success(voList, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }

    /**
     * 通过商品id 获取商品信息
     */
    @GetMapping("/good/{id}")
    public CommonResult getById(@PathVariable(value = "id") Integer id) {
        GoodCateStoreVO vo = goodService.getOneById(id);
        if (vo != null) {
            return CommonResult.success(vo, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }

    /**
     * 通过商家id 获取商品列表信息
     */
    @GetMapping("/store/{id}/goods")
    public CommonResult getByStoreId(@PathVariable(value = "id") Integer id) {
        List<GoodCateStoreVO> voList = goodService.listByStoreId(id);
        if (voList != null) {
            return CommonResult.success(voList, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }

    /**
     * 通过分类id 获取商品列表信息
     */
    @GetMapping("/category/{id}/goods")
    public CommonResult getByCateId(@PathVariable(value = "id") Integer id) {
        List<GoodCateStoreVO> voList = goodService.listByCateId(id);
        if (voList != null) {
            return CommonResult.success(voList, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }

    /**
     * 通过商家id和分类id 获取商品列表信息
     */
    @GetMapping("/store/{storeId}/category/{cateId}/goods")
    public CommonResult getByStoreCateId(@PathVariable(value = "storeId") Integer storeId,
                                         @PathVariable(value = "cateId") Integer cateId) {
        List<GoodCateStoreVO> voList = goodService.listByStoreCateId(storeId, cateId);
        if (voList != null) {
            return CommonResult.success(voList, "查询成功");
        } else {
            return CommonResult.failed("查询失败");
        }
    }

    /**
     * 新增商品信息
     */
    @PostMapping("/good")
    public CommonResult add(@RequestBody Good good) {
        // 先查询商品是否存在
        Good one = goodService.getById(good.getId());
        if (one != null) {
            // 商品已存在
            return CommonResult.failed("创建失败：商品已存在");
        }
        boolean hasSave = goodService.save(good);
        if (hasSave) {
            return CommonResult.success(null, "创建成功");
        } else {
            return CommonResult.failed("创建失败");
        }
    }

    /**
     * 更新商品信息
     */
    @PutMapping("/good")
    public CommonResult update(@RequestBody Good good) {
        boolean hasUpdate = goodService.updateById(good);
        if (hasUpdate) {
            return CommonResult.success(null, "更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    /**
     * 删除商品信息
     */
    @DeleteMapping("/good/{id}")
    public CommonResult remove(@PathVariable(value = "id") Integer id) {
        boolean hasRemove = goodService.removeById(id);
        if (hasRemove) {
            return CommonResult.success(null, "删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
}
