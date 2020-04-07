package com.wu.blog.controller;

import com.wu.blog.cache.HotTopicCache;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private HotTopicCache hotTopicCache;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PageDTO pageDTOs = questionService.list(search, tag, sort, page, size);
        List<String> tags = hotTopicCache.getHots();
        model.addAttribute("pageDTOs", pageDTOs);
        model.addAttribute("search", search);
        model.addAttribute("tags", tags);
        model.addAttribute("tag", tag);
        model.addAttribute("sort", sort);
        return "index";
    }
}
