package com.wu.blog.dto;

import com.wu.blog.domain.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long praiseCount;
    private String content;
    private User user;
}
