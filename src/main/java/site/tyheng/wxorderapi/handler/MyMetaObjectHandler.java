package site.tyheng.wxorderapi.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 配置自动填充功能
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时自动填充 creatTime 和 updateTime
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,
                "createTime",
                LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject,
                "updateTime",
                LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新时自动填充 updateTime
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,
                "updateTime",
                LocalDateTime.class, LocalDateTime.now());
    }
}
