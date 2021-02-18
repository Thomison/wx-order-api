package site.tyheng.wxorderapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.tyheng.wxorderapi.entity.Admin;

@Slf4j
@SpringBootTest
public class TestAdminService {

    @Autowired
    public IAdminService adminService;

    /**
     * 测试 通过用户名查找用户
     */
    @Test
    public void test() {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        Admin admin = adminService.getOne(
                queryWrapper.eq("login_name", "唐以恒"), false);
    }
}
