package com.wu.blog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"The problem you are looking for doesn't exist. Please try again later"),
    TARGET_PARAM_NOT_FOUND(2002,"No questions or comments seleted to reply"),
    NO_LOGIN(2003,"Unable to comment without login, please login first"),
    SYS_ERROR(2004,"The service was busy"),
    TYPE_PARAM_WRONG(2005,"Wrong or nonexistent comment type"),
    COMMENT_NOT_FOUND(2006,"Comment not found"),
    CONTENT_IS_EMPTY(2007,"Input can not be empty"),
    READ_NOTIFICATION_FAILURE(2008,"Can't read other's information"),
    NOTIFICATION_NOT_FOUND(2009,"The news disappeared");

    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code,String message) {
      this.code = code;
      this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
