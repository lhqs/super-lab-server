package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-05-30 21:11
 * description : mapper.class
 * version : 1.0
 */
@Mapper
public interface UserMapper {

    @Select(" select count(*) from user_info where (username = #{username} or mobile_phone = #{username} or email = #{username} ) and password = #{password}")
    int loginTest(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info where token = #{token}")
    UserInfo getUserInfo(@Param("token") String token);

    @Update("update user_info set request_nums = request_nums - 1  where token = #{token}")
    int updateRequestNums(@Param("token") String token);

    @Update("update user_info set login_num = login_num + 1, token = #{username}  where (username = #{username} or mobile_phone = #{username} or email = #{username} ) and password = #{password}")
    int updateUserToken(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info ")
    List<UserInfo> getGroupInfo();

}
