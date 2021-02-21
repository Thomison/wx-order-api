package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.User;

/**
 * @author tangyiheng
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //
}
