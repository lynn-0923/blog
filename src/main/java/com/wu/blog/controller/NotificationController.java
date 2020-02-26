package com.wu.blog.controller;

import com.wu.blog.domain.User;
import com.wu.blog.dto.NotificationDTO;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.enums.NotificationTypeEnum;
import com.wu.blog.service.NotificationService;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}")
    public  String notify(@PathVariable(name = "id") Long id,
                          HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO=notificationService.read(id,user);
        if(NotificationTypeEnum.REPLY_COMMENT.getType() ==notificationDTO.getType()
                 ||NotificationTypeEnum.REPLY_QUESTION.getType() ==notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOutid();
        }else {
            return "redirect:/";
        }
    }
}
