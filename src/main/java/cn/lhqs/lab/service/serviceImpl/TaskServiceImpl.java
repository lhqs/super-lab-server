package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.PageInfo;
import cn.lhqs.lab.entity.TaskList;
import cn.lhqs.lab.entity.TaskListVO;
import cn.lhqs.lab.mapper.TaskMapper;
import cn.lhqs.lab.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public PageInfo<TaskList> getTaskList(int pageNum, int pageSize) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<TaskList> pageInfo = new PageInfo<>();
        List<TaskList> taskLists = taskMapper.selectTaskList(realPageNum, pageSize);
        pageInfo.setTotal(taskMapper.total());
        pageInfo.setList(taskLists);
        return pageInfo;
    }

    @Override
    public List<TaskList> getTaskRecordByUserId(String userId) {
        return taskMapper.selectTaskByUserId(userId);
    }

    @Override
    public int addTaskList(TaskListVO taskListVO) {
        String taskName = taskListVO.getTaskName();
        String taskType = taskListVO.getTaskType();
        String describe = taskListVO.getDescribe();
        Date startDate = taskListVO.getStartDate();
        String prodecer = taskListVO.getProducer();
        Date endDate = taskListVO.getEndDate();
        int respCode = 0;
        for(String userId: taskListVO.getMemberName()){
            respCode = taskMapper.insertTaskList(taskName, taskType, describe, startDate, endDate,userId, prodecer, new Date());
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
}
