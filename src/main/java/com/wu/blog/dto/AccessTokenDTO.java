package com.wu.blog.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class AccessTokenDTO implements Serializable {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
