package com.wu.blog.controller;

import com.wu.blog.domain.Comment;
import com.wu.blog.domain.User;
import com.wu.blog.dto.CommentDTO;
import com.wu.blog.dto.ResultDTO;
import com.wu.blog.exception.CustomizeErrorCode;
import com.wu.blog.mapper.CommentMapper;
import com.wu.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  Object post(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setPraiseCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
