package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Admin;
import site.tyheng.wxorderapi.mapper.AdminMapper;
import site.tyheng.wxorderapi.service.IAdminService;

/**
 *
 * @author tangyiheng
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
}
