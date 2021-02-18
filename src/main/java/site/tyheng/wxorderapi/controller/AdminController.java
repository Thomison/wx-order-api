package site.tyheng.wxorderapi.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.tyheng.wxorderapi.entity.Admin;
import site.tyheng.wxorderapi.service.IAdminService;
import site.tyheng.wxorderapi.utils.CommonResult;

/**
 *
 * @author tangyiheng
 */
@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 处理管理员登录的post请求
     *
     * @param admin
     * @return CommonResult
     */
    @PostMapping("/admin/login")
    public CommonResult login(@RequestBody Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", admin.getName());
        Admin selectedAdmin = adminService.getOne(queryWrapper, false);

        if (selectedAdmin == null) {
            return CommonResult.failed("用户名不存在");
        } else {
            if (selectedAdmin.getPassword().equals(admin.getPassword())) {
                return CommonResult.success(null, "登录成功");
            } else {
                return CommonResult.failed("密码不正确");
            }
        }
    }

}
