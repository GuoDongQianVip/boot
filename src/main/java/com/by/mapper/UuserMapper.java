package com.by.mapper;

import com.by.model.User;
import com.by.model.UserExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.by.model.UserRole;
import com.by.vo.User1Vo;
import com.by.vo.UserRoleVo;
import com.by.vo.UserVo;
import org.apache.ibatis.annotations.*;
@Mapper
public interface UuserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

   /*@Select("select * from t_user")*/
    List<User1Vo> findAll(Map <String,Object>  mappage);
    @Insert("insert into t_user(user_name,user_pswd) values (#{userName},#{userPswd})")
    void add(User user);
    @Select("select * from t_user where user_id =#{userId}")
    User selectById(Integer userId);

    @Update("update from t_user set user_name=#{userName} user_pswd=#{userPswd}")
    void update(User user);
    @Select("select role_id from t_ur where user_id = #{userId}")
    List<Integer> selectIdsByUserId(@Param("userId") Integer userId);
    @Delete("delete from t_user where user_id = #{userId}")
    void deleteById(Integer userId);
    @Delete("delete from t_ur where user_id = #{userId}")
    void deleteIdByUserId(@Param("userId") Integer userId);

    void insertUserRole(@Param("userRoleVo") UserRoleVo userRoleVo);
    @Select("select * from t_user where user_name = #{userName}")
    User findByName(String username);

    Set<String> findRoleByUserName(@Param("userName") String userName);

    Set<String> findPermissionByUserName(@Param("userName") String userName);

    User selectByName(User user);

    void addUser(User user);
}