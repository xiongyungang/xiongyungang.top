package top.xiongyungang.service;

import top.xiongyungang.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiongyungang
 * @since 2020-12-06
 */
public interface ContentService extends IService<Content> {
    List<Content> list();
}
