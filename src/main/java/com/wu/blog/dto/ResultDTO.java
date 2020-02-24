package com.wu.blog.dto;

import com.wu.blog.exception.CustomizeErrorCode;
import com.wu.blog.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private String message;
    private Integer code;
    private T data;
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }
    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("Successful!");
        return resultDTO;
    }
    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("Request successful!");
        resultDTO.setData(t);
        return resultDTO;
    }
}
