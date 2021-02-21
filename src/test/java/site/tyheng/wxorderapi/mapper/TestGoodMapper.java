package site.tyheng.wxorderapi.mapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Good;
import site.tyheng.wxorderapi.entity.GoodCateStoreVO;

import java.util.List;

@Slf4j
@SpringBootTest
public class TestGoodMapper {

    @Autowired
    public GoodMapper goodMapper;

    @Test
    public void testSelect() {
//        List<Good> goodList = goodMapper.selectList(null);
        // 查询全部
//        List<GoodCateStoreVO> voList = goodMapper.selectAll();
//        log.info("{}", voList);
        // 根据id查询单条
        GoodCateStoreVO vo = goodMapper.selectById(1);
        log.info("{}", vo);
    }
}
