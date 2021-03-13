package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Record;
import site.tyheng.wxorderapi.mapper.RecordMapper;
import site.tyheng.wxorderapi.service.IRecordService;

/**
 * 处理用户访问记录的 service 实现类
 *
 * @author tangyiheng
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
}
