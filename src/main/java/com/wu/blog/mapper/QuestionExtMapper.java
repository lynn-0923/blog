package com.wu.blog.mapper;

import com.wu.blog.domain.Question;
import com.wu.blog.domain.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question question);
    int incCommentCount(Question question);
}