package cn.lhqs.lab.service;

import cn.lhqs.lab.entity.MemberVO;
import cn.lhqs.lab.entity.RegisterCode;
import cn.lhqs.lab.entity.UserInfo;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-05-30 21:16
 * description : UserService.class
 * version : 1.0
 */
public interface UserService {

    int loginTest(String username, String password);

    UserInfo getUserInfo(String token);

    int updateRequestNums(String token);

    int updateToken(String username, String password);

    List<UserInfo> getGroupMember();

    List<List<MemberVO>> getFullMember();

    int addRegisterCode(UserInfo userInfo, String code, String ip);

    String getRegisterCode();

    int updateImageUrl(String token, String imageUrl);

    int updateUserType(String userId, String userType, String userGroup);

    int reNewDesc(String token, String newDesc);

    int getNewCount();
}
