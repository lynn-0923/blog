package com.wu.blog.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wu.blog.domain.Question;
import com.wu.blog.domain.User;
import com.wu.blog.dto.QuestionDTO;
import com.wu.blog.mapper.QuestionMapper;
import com.wu.blog.mapper.UserMapper;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    private String edit(@PathVariable("id") Integer id,
                        Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public  String publish(){
        return  "publish";
    }
    @PostMapping("/publish")
    public  String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user= (User) request.getSession().getAttribute("user");
        if(user ==null){
            model.addAttribute("error","User not logged in");
            return "publish";
        }
        if(title ==null|| title ==""){
            model.addAttribute("error","Title cannot be empty");
            return "publish";
        }if(description ==null || description ==""){
            model.addAttribute("error","Supplementary question cannot be empty ");
            return "publish";
        }if(tag ==null || tag ==""){
            model.addAttribute("error","Tag cannot be empty");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}