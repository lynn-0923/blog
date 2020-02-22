package com.wu.blog.dto;

import com.wu.blog.domain.User;
import lombok.Data;

import java.io.Serializable;
@Data
public class QuestionDTO implements Serializable{
        private Integer id;
        private String title;
        private String description;
        private Long gmtCreate;
        private Long gmtModified;
        private Integer creator;
        private Integer commentCount;
        private Integer viewCount;
        private Integer praiseCount;
        private String tag;
        private User user;

}