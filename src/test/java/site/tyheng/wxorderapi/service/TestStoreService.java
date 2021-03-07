package site.tyheng.wxorderapi.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Store;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class TestStoreService {

    @Autowired
    public IStoreService storeService;

//    @Test
//    public void testInsert() {
//        Store a = Store.builder().name("店铺A").build();
//        Store b = Store.builder().name("店铺B").build();
//        Store c = Store.builder().name("店铺C").build();
//        boolean hasSave = storeService.saveBatch(Arrays.asList(a, b, c));
//    }
}
