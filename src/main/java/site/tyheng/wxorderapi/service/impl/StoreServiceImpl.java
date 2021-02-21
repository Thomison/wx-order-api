package site.tyheng.wxorderapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.tyheng.wxorderapi.entity.Store;
import site.tyheng.wxorderapi.mapper.StoreMapper;
import site.tyheng.wxorderapi.service.IStoreService;

/**
 * 商店信息的 service实现类
 * @author tangyiheng
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {
}
