package site.tyheng.wxorderapi.mapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestOrderMapper {
    @Autowired
    public OrderMapper orderMapper;
    @Test
    public void test() {
        orderMapper.getOrders();
    }
}
