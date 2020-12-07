package top.xiongyungang.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.xiongyungang.service.ContentService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiongyungang
 * @since 2020-12-06
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;

    @GetMapping("list")
    public Object list() {
        return contentService.list();
    }
}

