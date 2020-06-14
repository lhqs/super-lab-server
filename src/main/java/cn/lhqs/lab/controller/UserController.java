package cn.lhqs.lab.controller;


import cn.lhqs.lab.entity.MemberVO;
import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.entity.UserInfo;
import cn.lhqs.lab.entity.UserVO;
import cn.lhqs.lab.service.UserService;
import cn.lhqs.lab.utils.IpAddrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-05-29 23:30
 * description : LoginController.class
 * version : 1.0
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Resource
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/login")
    public ReturnResult login(@RequestBody UserVO user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("username -->"+user.getUsername() + ";  password -->"+user.getPassword());
        int respCode = userService.loginTest(user.getUsername(), user.getPassword());
        Map<String, String> map = new HashMap<>();
        map.put("token", user.getUsername());
        int respStatus = userService.updateToken(user.getUsername(), user.getPassword());
        if (respCode == 1 && respStatus == 1){
            return new ReturnResult(200,"success",map);
        }
        return new ReturnResult(-2,"fail","登录验证不通过");
    }

    @PostMapping("/user/register")
    public ReturnResult register(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("userInfo -->"+userInfo);
        Map<String, String> map = new HashMap<>();
        map.put("token", userInfo.getUsername());
        int respCode = userService.addRegisterCode(userInfo, "SUPERLABT", IpAddrUtils.getIpAddr(request));
        System.out.println(respCode);
        if (respCode == 1){
            return new ReturnResult(200,"success",map);
        }
        return new ReturnResult(-2,"fail","注册失败");
    }

    @GetMapping("/user/info")
    public ReturnResult getobject(HttpServletRequest request) {
        String token = request.getHeader("userToken");
        logger.info(" getInfo token --->" + token);
        UserInfo userInfo = userService.getUserInfo(token);
        return new ReturnResult(200,"success",userInfo);
    }

    @PostMapping("/user/logout")
    public ReturnResult logout(HttpServletRequest request, HttpServletResponse response ) {
        String user = request.getHeader("userToken");
        // String token = CookieUtils.getCookieValue(request, "test-cookie");  // axios默认是发送请求的时候不会带上cookie的，需要
        // 通过设置withCredentials: true来解决，此时后台Access-Control-Allow-Origin不可以为 '*'，因为 '*' 会和 Access-Control-Allow-Credentials:true 冲突，需配置指定的地址
        logger.info( user + "退出登录");
        return new ReturnResult(200,"success","succes");
    }

    @GetMapping("/user/getGroupInfo")
    public ReturnResult getGroupInfo(HttpServletRequest request) {
        List<UserInfo> userInfos = userService.getGroupMember();
        return new ReturnResult(200,"success", userInfos);
    }

    @GetMapping("/user/updateUserType")
    public ReturnResult updateUserType(@RequestParam(value = "userId") String userId,@RequestParam(value = "userType") String userType,
                                        @RequestParam(value = "userGroup") String userGroup) {
        int respCode = userService.updateUserType(userId, userType, userGroup);
        if(respCode != 0) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/user/getAllMember")
    public ReturnResult getAllMember(HttpServletRequest request) {
        List<List<MemberVO>> memberVOS = userService.getFullMember();
        return new ReturnResult(200,"success", memberVOS);
    }

    @GetMapping("/user/isValidateCode")
    public ReturnResult isValidateCode(@RequestParam(value = "registerCode",  defaultValue = "") String registerCode, HttpServletRequest request) {
        String returnCode = userService.getRegisterCode();
        if(returnCode.equals(registerCode.trim())) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/user/updateDesc")
    public ReturnResult updateDesc(@RequestParam(value = "newDesc") String newDesc, HttpServletRequest request) {
        String token = request.getHeader("userToken");
        logger.info("the get_token key --->"+token);
        int respCode = userService.reNewDesc(token,newDesc);
        if(respCode != 0) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/user/getNewUserNum")
    public ReturnResult getNewUserNum() {
        int num = userService.getNewCount();
        return new ReturnResult(200, "success", num);
    }
}
