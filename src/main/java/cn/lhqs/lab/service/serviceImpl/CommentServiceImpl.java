package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.CommentInfo;
import cn.lhqs.lab.entity.PublishTalkVO;
import cn.lhqs.lab.entity.TaskInfoList;
import cn.lhqs.lab.entity.TaskList;
import cn.lhqs.lab.mapper.CommentMapper;
import cn.lhqs.lab.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-12-08 13:43
 * description : CommentServiceImpl.class
 * version : 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {


    @SuppressWarnings("all")
    @Resource
    CommentMapper commentMapper;

    @Override
    @Transactional
    public List<TaskInfoList> getTaskInfoList(String userId) {
        List<TaskInfoList>  taskInfoLists = new ArrayList<>();

        String returnCode = commentMapper.selectUserType(userId);
        System.out.println("return code : " + returnCode);
        if(returnCode.equals("1")) {
            List<TaskList> taskLists = commentMapper.selectTaskList(userId);
            for(TaskList taskList : taskLists) {
                TaskInfoList taskInfoList = new TaskInfoList();
                taskInfoList.setTaskId(taskList.getTaskId());
                taskInfoList.setTaskName(taskList.getTaskName());
                final List<CommentInfo> commentInfos = commentMapper.selectCommentByTaskName(taskList.getTaskName());
                taskInfoList.setList(commentInfos);
                taskInfoLists.add(taskInfoList);
            }
        } else {
            List<TaskList> taskLists = commentMapper.selectAllTaskList();
            for(TaskList taskList : taskLists) {
                TaskInfoList taskInfoList = new TaskInfoList();
                taskInfoList.setTaskId(taskList.getTaskId());
                taskInfoList.setTaskName(taskList.getTaskName());
                final List<CommentInfo> commentInfos = commentMapper.selectCommentByTaskName(taskList.getTaskName());
                taskInfoList.setList(commentInfos);
                taskInfoLists.add(taskInfoList);
            }
        }


        return taskInfoLists;
    }

    @Override
    public int insertComment(PublishTalkVO publishTalkVO) {
        publishTalkVO.setCreateTime(new Date());
        return commentMapper.insertComment(publishTalkVO);
    }

    @Override
    public int removeTaskByTaskId(String taskName, String userId) {
        String returnCode = commentMapper.selectUserType(userId);
        System.out.println("return code : " + returnCode);
        if(returnCode.equals("1")) {
            return 1;
        }
        int respComment = commentMapper.updateTalkStatus(taskName);
        int respStatus = commentMapper.updateCommentStatus(taskName);
        return respComment + respStatus + 2;
    }
}
