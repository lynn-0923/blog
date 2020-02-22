package com.wu.blog.controller;

import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.mapper.QuestionMapper;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return  "question";
    }
}
