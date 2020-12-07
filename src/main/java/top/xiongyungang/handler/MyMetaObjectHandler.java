package top.xiongyungang.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());

//        Date date = new Date();
//        this.setFieldValByName("create_time", date, metaObject);
//        this.setFieldValByName("update_time", date, metaObject);
    }

    // 更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateFill");
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
//        Date date = new Date();
//        this.setFieldValByName("update_time", date, metaObject);
    }
}
