package cn.lhqs.lab.service;

import cn.lhqs.lab.entity.PageInfo;
import cn.lhqs.lab.entity.TaskList;
import cn.lhqs.lab.entity.TaskListVO;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-05 1:07
 * description : TaskService.class
 * version : 1.0
 */
public interface TaskService {

    PageInfo<TaskList> getTaskList(int pageNum, int pageSize, String taskConsumer, String taskProducer, String tags,String userId);

    PageInfo<TaskList> getTaskCompleteList(int pageNum, int pageSize, String taskConsumer, String taskProducer, String tags, String userId);

    List<TaskList> getTaskRecordByUserId(String userId);

    int addTaskList(TaskListVO taskListVO);

    int removeTaskById(String taskId);

    int updateVerifyStatus(String taskId);

    int renewProgressById(String taskId, int progress);

    int getUnreadMessage(String userId);

    List<TaskList> getTaskListByUserId(String userId);

    int updateReadState(String taskId);
}
