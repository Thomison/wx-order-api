package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Admin;


/**
 * 处理管理员数据的 mapper
 * @author tangyiheng
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    //
}
