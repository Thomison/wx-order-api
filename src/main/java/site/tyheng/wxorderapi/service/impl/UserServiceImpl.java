package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.User;
import site.tyheng.wxorderapi.mapper.UserMapper;
import site.tyheng.wxorderapi.service.IUserService;

/**
 * @author tangyiheng
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
