package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.PageInfo;
import cn.lhqs.lab.entity.TaskList;
import cn.lhqs.lab.entity.TaskListVO;
import cn.lhqs.lab.mapper.TaskMapper;
import cn.lhqs.lab.mapper.UserMapper;
import cn.lhqs.lab.service.TaskService;
import cn.lhqs.lab.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-05 1:07
 * description : TaskServiceImpl.class
 * version : 1.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @SuppressWarnings("all")
    @Resource
    TaskMapper taskMapper;

    @SuppressWarnings("all")
    @Resource
    UserMapper userMapper;


    @Override
    public PageInfo<TaskList> getTaskList(int pageNum, int pageSize, String taskConsumer, String taskProducer, String tags, String userId) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<TaskList> pageInfo = new PageInfo<>();

        String userType = taskMapper.getUserType(userId);

        List<TaskList> taskLists = taskMapper.selectTaskList(realPageNum, pageSize, taskConsumer, taskProducer,tags,userType, userId);
        pageInfo.setTotal(taskMapper.total( taskConsumer, taskProducer,tags,userType, userId));
        pageInfo.setList(taskLists);
        return pageInfo;
    }

    @Override
    public PageInfo<TaskList> getTaskCompleteList(int pageNum, int pageSize, String taskConsumer, String taskProducer, String tags, String userId) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<TaskList> pageInfo = new PageInfo<>();

        String userType = taskMapper.getUserType(userId);

        List<TaskList> taskLists = taskMapper.selectCompleteTaskList(realPageNum, pageSize, taskConsumer, taskProducer, tags, userType, userId);
        pageInfo.setTotal(taskMapper.totaled(taskConsumer, taskProducer, tags, userType, userId));
        pageInfo.setList(taskLists);
        return pageInfo;
    }

    @Override
    public List<TaskList> getTaskRecordByUserId(String userId) {
        List<TaskList> taskLists = taskMapper.selectTaskByUserId(userId);
        List<TaskList> list = new ArrayList<>();
        for (TaskList taskList : taskLists) {
            TaskList task = new TaskList();
            BeanUtils.copyProperties(taskList, task);
            task.setTaskType(Constants.maps.get(taskList.getTaskType()));
            list.add(task);
        }
        return list;
    }

    @Override
    public int addTaskList(TaskListVO taskListVO) {
        String taskName = taskListVO.getTaskName();
        String taskType = taskListVO.getTaskType();
        String describe = taskListVO.getDescribe();
        Date startDate = taskListVO.getStartDate();
        String producer = taskListVO.getProducer();
        String address = taskListVO.getAddress();
        double budget = taskListVO.getBudget();
        Date endDate = taskListVO.getEndDate();
        int isEmergency = taskListVO.getIsEmergency();
        int respCode = 0;
        for(String username: taskListVO.getMemberName()){
            String userId = userMapper.getUserIdByUsername(username);
            respCode = taskMapper.insertTaskList(taskName, taskType, describe, startDate, endDate,userId, producer, new Date(),address, budget, isEmergency);
        }
        return respCode;
    }

    @Override
    public int removeTaskById(String taskId) {
        return taskMapper.deleteTaskById(taskId);
    }

    @Override
    public int updateVerifyStatus(String taskId) {
        return taskMapper.updateVerify(taskId);
    }

    @Override
    public int renewProgressById(String taskId, int progress) {
        return taskMapper.updateProgressById(taskId, progress);
    }

    @Override
    public int getUnreadMessage(String userId) {
        String userType = taskMapper.getUserType(userId);
        if(userType.equals("2")) {
            System.out.println("---------------------------------------");
            return userMapper.getNewRegisterUserCount();
        }
        return taskMapper.getUnreadMsg(userId);
    }

    @Override
    public List<TaskList> getTaskListByUserId(String userId) {

        return taskMapper.getUnreadMsgList(userId);
    }

    @Override
    public int updateReadState(String taskId) {
        return taskMapper.updateReadState(taskId);
    }
}
