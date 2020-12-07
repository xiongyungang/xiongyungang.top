package top.xiongyungang.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.xiongyungang.entity.Content;
import top.xiongyungang.mapper.ContentMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentMapperTest {
    @Resource
    ContentMapper contentMapper;

    @Test
    public void list() throws Exception {
        List<Content> list = contentMapper.selectList(null);
        System.out.println("list = " + list);
    }

    @Test
    public void insert() {
        Content content = new Content();
        content.setAuthor("Tom");
        content.setTitle("Everyone and Everything");
        content.setContent("long long ago..");
        // 返回受影响的行数
        int insert = contentMapper.insert(content);
        System.out.println("insert = " + insert);
    }

    @Test
    public void update() {
        Content content = new Content();
        content.setId(126L);
        content.setAuthor("Tom");
        content.setTitle("Everyone and Everything");
        content.setContent("long long ago..");
        int i = contentMapper.updateById(content);
        System.out.println("i = " + i);
    }

    /**
     *
     * SELECT id,author,content,title,category_id AS category_id,user_id AS user_id,version,create_time AS create_time,update_time AS update_time FROM content WHERE id=?
     *
     * SELECT id,author,content,title,category_id AS category_id,user_id AS user_id,version,create_time AS create_time,update_time AS update_time FROM content WHERE id=?
     *
     * UPDATE content SET author=?, content=?, title=?, version=?, update_time=? WHERE id=? AND version=?
     *
     * UPDATE content SET author=?, content=?, title=?, version=?, update_time=? WHERE id=? AND version=?
     *
     */
    @Test
    public void lock() {
        // 查询时获取version
        Content content = contentMapper.selectById(126);

        // 模拟更新操作被插队（可能时其他线程）
        Content content1 = contentMapper.selectById(126);
        content1.setAuthor("Link");
        contentMapper.updateById(content1); // 此处已经将version更新

        content.setAuthor("Nobita"); // 未更新成功，version被修改了
        // 更新时进行version比对，确定没有被人修改,再更新version
        contentMapper.updateById(content);
    }
    /**
     *
     * SELECT id,author,content,title,category_id AS category_id,user_id AS user_id,version,create_time AS create_time,update_time AS update_time FROM content WHERE id=?
     *
     *UPDATE content SET author=?, content=?, title=?, version=?, update_time=? WHERE id=? AND version=?
     *
     */

    @Test
    public void page() {
        Page<Content> objectPage = new Page<>(1, 1);
        IPage<Content> contentIPage = contentMapper.selectPage(objectPage, null);

        contentIPage.getRecords().forEach(System.out::println);
        System.out.println(contentIPage.getTotal()); // 获取总页数
        System.out.println(contentIPage.getPages()); // 获取总数
        System.out.println(contentIPage.getSize());  // 分页大小
        System.out.println(contentIPage.getCurrent()); // 当前记录位置
    }
    /**
     * SELECT id,author,content,title,category_id AS category_id,user_id AS user_id,version,create_time AS create_time,update_time AS update_time FROM content LIMIT 0,1
     *
     */

    @Test
    public void page3() {
        Page<Content> objectPage = new Page<>(1, 1);
        IPage<Content> contentIPage = contentMapper.selectPageVo(objectPage);
        System.out.println(contentIPage.getRecords());
    }

    /**
     * UPDATE content SET deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    public void delete() {
        contentMapper.deleteById(126L);
    }

    /**
     * SELECT id,author,content,title,category_id AS category_id,user_id AS user_id,version,deleted,create_time AS create_time,update_time AS update_time FROM content WHERE deleted=0 AND author >= ? AND title IS NOT NULL
     */
    @Test
    public void select() {
        QueryWrapper<Content> wrapper = new QueryWrapper<>();
        wrapper.eq("author", "Tom")
                .isNotNull("title")
                .orderByDesc("create_time"); // 排序
        List<Content> contents = contentMapper.selectList(wrapper);
        System.out.println("contents = " + contents);
    }
}