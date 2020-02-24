package com.wu.blog.mapper;

import com.wu.blog.domain.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}