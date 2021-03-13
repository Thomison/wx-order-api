package site.tyheng.wxorderapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import site.tyheng.wxorderapi.entity.Record;

/**
 * 处理用户访问记录的 mapper
 *
 * @author tangyiheng
 */
@Repository
public interface RecordMapper extends BaseMapper<Record> {
}
