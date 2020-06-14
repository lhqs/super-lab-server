package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.*;
import cn.lhqs.lab.service.TaskService;
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
 * createTime : 2018-09-04 19:21
 * description : TaskController.class
 * version : 1.0
 */

@RestController
@RequestMapping("api")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Resource
    TaskService taskServiceImpl;

    @PostMapping("/task/addTaskList")
    public ReturnResult addTaskList(@RequestBody TaskListVO taskListVO, HttpServletRequest request, HttpServletResponse response) {
        logger.info("taskListVO -->"+taskListVO);
        int respCode = taskServiceImpl.addTaskList(taskListVO);
        if (respCode == 1){
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/task/getTaskList")
    public ReturnResult getTaskList(@RequestParam(value = "pageNum",  defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "taskConsumer",  defaultValue = "") String taskConsumer,
                                    @RequestParam(value = "taskProducer", defaultValue = "") String taskProducer,
                                    @RequestParam("isComplete") int isComplete, @RequestParam("tags")String tags,
                                    @RequestParam("userId")String userId) {
        logger.info("pageNum -->"+pageNum + ", pageSize -->" + pageSize + ", taskConsumer --> " + taskConsumer + ", taskProducer --> " + taskProducer + ", isComplete--> " + isComplete);
        PageInfo<TaskList> taskList = null;
        if (isComplete == 0) {
            taskList = taskServiceImpl.getTaskList(pageNum, pageSize, taskConsumer, taskProducer, tags, userId);
        } else {
            taskList = taskServiceImpl.getTaskCompleteList(pageNum, pageSize, taskConsumer, taskProducer, tags, userId);
        }
        return new ReturnResult(200, "success", taskList);
    }

    @GetMapping("/task/getTaskRecord")
    public ReturnResult getTaskList(@RequestParam("userId")String userId) {
        logger.info("userId ---> " + userId);
        List<TaskList> taskList = taskServiceImpl.getTaskRecordByUserId(userId);
        return new ReturnResult(200, "success", taskList);
    }

    @DeleteMapping("/task/deleteTaskById")
    public  ReturnResult deleteTaskById(@RequestParam("taskId")String taskId) {
        logger.info("taskId ---> " + taskId);
        int respCode = taskServiceImpl.removeTaskById(taskId);
        if(respCode == 1) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/task/verifyOperate")
    public ReturnResult verifyOperate(@RequestParam("taskId")String taskId) {
        logger.info("taskId ---> " + taskId);
        int respCode = taskServiceImpl.updateVerifyStatus(taskId);
        if(respCode == 1) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/task/renewProgress")
    public ReturnResult verifyOperate(@RequestParam("taskId")String taskId, @RequestParam("progress") int progress) {
        logger.info("progress ---> " + progress + "; taskId ---> " + taskId);
        int respCode = taskServiceImpl.renewProgressById(taskId, progress);
        if(respCode == 1) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/task/getUnreadMsg")
    public ReturnResult getUnreadMsg(@RequestParam("userId")String userId) {
        logger.info("userId ---> " + userId );
        int count = taskServiceImpl.getUnreadMessage(userId);
        return new ReturnResult(200, "success", count);
    }

    @GetMapping("/task/getUnreadMsgList")
    public ReturnResult getUnreadMsgList(@RequestParam("userId")String userId) {
        logger.info("userId ---> " + userId );
        List<TaskList> taskList = taskServiceImpl.getTaskListByUserId(userId);
        return new ReturnResult(200, "success", taskList);
    }

    @GetMapping("/task/updateReadState")
    public ReturnResult updateReadState(@RequestParam("taskId")String taskId) {
        logger.info("taskId ---> " + taskId );
        int respCode = taskServiceImpl.updateReadState(taskId);
        if(respCode == 1) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }


}
