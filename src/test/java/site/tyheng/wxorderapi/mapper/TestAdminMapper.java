package site.tyheng.wxorderapi.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Admin;

@Slf4j
@SpringBootTest
public class TestAdminMapper {

    @Autowired
    public AdminMapper adminMapper;

    /**
     *  测试插入新纪录
     *  插入重复用户名的记录将会导致插入失败
     */
//    @Test
//    public void testInsert() {
//        Admin admin = Admin.builder()
//                .name("唐以恒")
//                .password("123456")
//                .build();
//        adminMapper.insert(admin);
//    }

    /**
     * 测试根据用户名查找用户
     */
//    @Test
//    public void testSelect() {
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        Admin admin = adminMapper.selectOne(
//                wrapper.eq("login_name", "唐以恒"));
//        log.info("查询结果：{}", admin);
//    }


}
