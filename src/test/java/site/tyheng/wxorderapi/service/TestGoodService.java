package site.tyheng.wxorderapi.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class TestGoodService {
    @Autowired
    public IGoodService goodService;

    @Test
    public void testSave() {
        // 批量插入数据
    }

    @Test void testGet() {
        // 查询所有
//        List<GoodCateStoreVO> voList = goodService.listAll();
//        log.info("{}", voList);

        // 查询单个
//        GoodCateStoreVO vo = goodService.getOneById(10);
//        log.info("{}", vo);

        // 根据商家查询列表
//        List<GoodCateStoreVO> voList = goodService.listByStoreId(1);

        // 根据分类查询列表
//        List<GoodCateStoreVO> voList = goodService.listByCateId(1);

        // 根据商家和分类查询列表
//        List<GoodCateStoreVO> voList = goodService.listByStoreCateId(1, 1);
    }
}
