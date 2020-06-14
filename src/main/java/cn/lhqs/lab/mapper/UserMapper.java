package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.RegisterCode;
import cn.lhqs.lab.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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

    @Update("update user_info set avatar = #{imageUrl}  where token = #{token}")
    int updateImage(@Param("token") String token, @Param("imageUrl") String imageUrl);

    @Update("update user_info set login_num = login_num + 1, token = #{username}  where (username = #{username} or mobile_phone = #{username} or email = #{username} ) and password = #{password}")
    int updateUserToken(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info ")
    List<UserInfo> getGroupInfo();

    @Select("select register_code from register_manager limit 1")
    String getRegisterCode();

    @Insert(" insert into register_manager (register_code, register_name, request_ip, request_address, request_time) values(#{registerCode}, #{registerName}, #{requestIp}, #{requestAddress}, #{createTime} ) ")
    int insertRegister(RegisterCode registerCode);

    @Insert(" insert into user_info (token, username, password, mobile_phone, email, sex, address, create_time) values(#{token}, #{username}, #{password}, #{mobilePhone}, #{email}, #{sex},#{address},  #{createTime} ) ")
    int insertUserList(UserInfo userInfo);

    @Select("select user_id from user_info where username = #{username} ")
    String getUserIdByUsername(@Param("username") String username);

    @Update("update user_info set character_desc = #{newDesc}  where token = #{token}")
    int updateDesc(@Param("token") String token, @Param("newDesc") String newDesc);

    @Update("update user_info set user_type = #{userType},user_group = #{userGroup}, is_new = 1  where user_id = #{userId}")
    int updateUserType(@Param("userId") String userId, @Param("userType") String userType, @Param("userGroup") String userGroup);

    @Select("select count(*) from user_info where is_new = 0")
    int getNewRegisterUserCount();

}
