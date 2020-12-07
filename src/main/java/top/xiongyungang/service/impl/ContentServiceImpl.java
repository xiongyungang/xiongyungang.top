package top.xiongyungang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.xiongyungang.entity.Content;
import top.xiongyungang.mapper.ContentMapper;
import top.xiongyungang.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiongyungang
 * @since 2020-12-06
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Autowired
    ContentMapper contentMapper;

    @Override
    public List<Content> list() {
        return contentMapper.selectList(null);
    }
}
