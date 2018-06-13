package cn.lhqs.lab.service.serviceImpl;


import cn.lhqs.lab.entity.UserInfo;
import cn.lhqs.lab.mapper.UserMapper;
import cn.lhqs.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-05-30 21:17
 * description : UserServiceImpl.class
 * version : 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    @Override
    public int loginTest(String username, String password) {
        return userMapper.loginTest(username, password);
    }

    @Override
    public UserInfo getUserInfo(String token) {
        return userMapper.getUserInfo(token);
    }

    @Override
    public int updateRequestNums(String token) {
        return userMapper.updateRequestNums(token);
    }
}