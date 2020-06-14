package cn.lhqs.lab.service.serviceImpl;


import cn.lhqs.lab.entity.MemberVO;
import cn.lhqs.lab.entity.RegisterCode;
import cn.lhqs.lab.entity.UserInfo;
import cn.lhqs.lab.mapper.UserMapper;
import cn.lhqs.lab.service.UserService;
import cn.lhqs.lab.utils.IpAddrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.pipe.RegionIterator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    public List<List<MemberVO>> getFullMember() {
        List<UserInfo> groupInfos = userMapper.getGroupInfo();
        List<List<MemberVO>> lists = new ArrayList<>();
        List<MemberVO> memberVOS1 = new ArrayList<>();
        List<MemberVO> memberVOS2 = new ArrayList<>();
        List<MemberVO> memberVOS3 = new ArrayList<>();
        for (UserInfo userInfo : groupInfos) {
            if(userInfo.getUserGroup() == 1){
                MemberVO memberVO1 = new MemberVO();
                memberVO1.setLabel(userInfo.getUsername());
                memberVO1.setValue(userInfo.getUserId());
                memberVO1.setAvatar(userInfo.getAvatar());
                memberVOS1.add(memberVO1);
            } else if (userInfo.getUserGroup() == 2){
                MemberVO memberVO2 = new MemberVO();
                memberVO2.setLabel(userInfo.getUsername());
                memberVO2.setValue(userInfo.getUserId());
                memberVO2.setAvatar(userInfo.getAvatar());
                memberVOS2.add(memberVO2);
            } else if (userInfo.getUserGroup() == 3) {
                MemberVO memberVO3 = new MemberVO();
                memberVO3.setLabel(userInfo.getUsername());
                memberVO3.setValue(userInfo.getUserId());
                memberVO3.setAvatar(userInfo.getAvatar());
                memberVOS3.add(memberVO3);
            }
        }
        lists.add(memberVOS1);
        lists.add(memberVOS2);
        lists.add(memberVOS3);
        return lists;
    }

    @Override
    @Transactional
    public int addRegisterCode(UserInfo userInfo, String code, String ip) {
        String address = IpAddrUtils.getRealAddressByIP(ip);
        RegisterCode registerCode = new RegisterCode();
        registerCode.setRegisterName(userInfo.getUsername());
        registerCode.setRegisterCode(code);
        registerCode.setRequestAddress(address);
        registerCode.setRequestIp(ip);
        registerCode.setCreateTime(new Date());

        userInfo.setToken(userInfo.getUsername());
        userInfo.setCreateTime(new Date());
        userInfo.setAddress(address);
        int respStatus = userMapper.insertUserList(userInfo);
        int respCode = userMapper.insertRegister(registerCode);
        return respStatus & respCode;
    }

    @Override
    public String getRegisterCode() {
        return userMapper.getRegisterCode();
    }

    @Override
    public int updateImageUrl(String token, String imageUrl) {
        return userMapper.updateImage(token, imageUrl);
    }

    @Override
    public int updateUserType(String userId, String userType, String userGroup) {
        return userMapper.updateUserType(userId, userType, userGroup);
    }

    @Override
    public int reNewDesc(String token, String newDesc) {
        return userMapper.updateDesc(token, newDesc);
    }

    @Override
    public int getNewCount() {
        return userMapper.getNewRegisterUserCount();
    }

}