package site.tyheng.wxorderapi.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 *
 * @author tangyiheng
 */
@Data
@Builder
@TableName(value = "user_info")
public class User {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户open_id
     */
    @TableField(value = "open_id")
    private String openId;
    /**
     * 用户skey
     */
    private String skey;
    /**
     * 用户session_key
     */
    @TableField(value = "session_key")
    private String sessionKey;
    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;
    /**
     * 用户头像url路径
     */
    @TableField(value = "avatar_url")
    private String avatar_url;
    /**
     * 用户性别
     */
    private int gender;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 是否采取价格混淆策略
     */
    @TableField(value = "is_confuse")
    private boolean confuse;
    /**
     * 用户余额
     */
    private int money;
    /**
     * 用户最后一次登录时间
     */
    @TableField(value = "last_visit_time")
    private LocalDateTime lastVisitTime;
    /**
     * 记录创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 记录修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
