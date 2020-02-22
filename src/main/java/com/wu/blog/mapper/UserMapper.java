package com.wu.blog.mapper;

import com.wu.blog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);

    @Select("select * from user where id =#{id} ")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id =#{accountId} ")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set gmt_modified=#{gmtModified},name=#{name},token=#{token},avatar_url=#{avatarUrl} where account_id =#{accountId}")
    void update(User user);
}
