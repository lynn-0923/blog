package com.wu.blog.dto;

import com.wu.blog.domain.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outid;
    private String typeName;
    private Integer type;

}
