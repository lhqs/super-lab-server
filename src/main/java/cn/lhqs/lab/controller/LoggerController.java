package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.entity.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-02 15:02
 * description : LoggerController.class
 * version : 1.0
 */

@RestController
@RequestMapping("api")
public class LoggerController {
    private static Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @GetMapping("/log/getLogInfo")
    public ReturnResult getLogInfo(@RequestParam("content")String content, HttpServletRequest request, HttpServletResponse response) {
        logger.info("content:" + content);
        return new ReturnResult(-2,"fail","登录验证不通过");
    }
}
