package com.wu.blog.mapper;

import com.wu.blog.domain.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    @Insert("insert into question(id,title,description,gmt_create,gmt_modified,creator,tag) values(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select  * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question")
    Integer count();

    @Select("select  * from question  where creator=#{uid} limit #{offset},#{size}")
    List<Question> listByUid(@Param("uid") Integer uid, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question where creator =#{uid}")
    Integer countByUid(@Param("uid") Integer uid);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set gmt_modified=#{gmtModified},title=#{title},tag=#{tag},description=#{description} where id =#{id}")
    void update(Question question);
}
