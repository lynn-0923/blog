package com.wu.blog.controller;

import com.wu.blog.domain.Question;
import com.wu.blog.domain.User;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.mapper.QuestionMapper;
import com.wu.blog.mapper.UserMapper;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public  String index(HttpServletRequest request,
                         Model model,
                         @RequestParam(value = "page",defaultValue ="1") Integer page,
                         @RequestParam(value = "size",defaultValue = "5") Integer size){
        PageDTO pageDTOs=questionService.list(page,size);
        model.addAttribute("pageDTOs",pageDTOs);
        return "index";
    }
}
