package cn.lhqs.lab.service;

import cn.lhqs.lab.entity.PublishTalkVO;
import cn.lhqs.lab.entity.TaskInfoList;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-12-08 13:42
 * description : CommentService.class
 * version : 1.0
 */
public interface CommentService {

    List<TaskInfoList> getTaskInfoList(String userId);

    int insertComment(PublishTalkVO publishTalkVO);

    int removeTaskByTaskId(String taskName, String userId);
}
