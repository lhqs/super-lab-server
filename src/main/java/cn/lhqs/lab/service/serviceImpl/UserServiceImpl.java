package cn.lhqs.lab.service.serviceImpl;


import cn.lhqs.lab.entity.MemberVO;
import cn.lhqs.lab.entity.UserInfo;
import cn.lhqs.lab.mapper.UserMapper;
import cn.lhqs.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
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

    @Override
    public int updateToken(String username, String password) {
        return userMapper.updateUserToken( username, password);
    }

    @Override
    public List<UserInfo> getGroupMember() {
        return userMapper.getGroupInfo();
    }

    @Override
    public List<MemberVO> getFullMember() {
        List<UserInfo> groupInfos = userMapper.getGroupInfo();
        List<MemberVO> memberVOS = new ArrayList<>();
        for (UserInfo userInfo : groupInfos) {
            MemberVO memberVO = new MemberVO();
            memberVO.setLabel(userInfo.getUsername());
            memberVO.setValue(userInfo.getUserId());
            memberVOS.add(memberVO);
        }
        return memberVOS;
    }
}