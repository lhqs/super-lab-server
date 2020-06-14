package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.PublishTalkVO;
import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.entity.TaskInfoList;
import cn.lhqs.lab.entity.TaskListVO;
import cn.lhqs.lab.service.CommentService;
import cn.lhqs.lab.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-12-08 13:42
 * description : CommentController.class
 * version : 1.0
 */
@RequestMapping("api")
@RestController
public class CommentController {

    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Resource
    CommentService commentServiceImpl;

    @GetMapping("/comment/getCommentList")
    public ReturnResult getCommentList(@RequestParam("userId")String userId) {
        logger.info("the userId is : " + userId);
        List<TaskInfoList> taskInfoList = commentServiceImpl.getTaskInfoList(userId);
        return new ReturnResult(200, "success", taskInfoList);
    }

    @PostMapping("/comment/addComment")
    public ReturnResult addComment(@RequestBody PublishTalkVO publishTalkVO) {
        logger.info("publishTalkVO -->"+publishTalkVO);
        int respCode = commentServiceImpl.insertComment(publishTalkVO);
        if (respCode == 1){
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @DeleteMapping("/comment/deleteTalk")
    public  ReturnResult deleteTalk(@RequestParam("taskName")String taskName, @RequestParam("userId")String userId) {
        int respCode = commentServiceImpl.removeTaskByTaskId(taskName, userId);
        if(respCode == 1) {
            return  new ReturnResult(403,"你的权限不能删除");
        }
        return ReturnResult.ok;
    }

}
