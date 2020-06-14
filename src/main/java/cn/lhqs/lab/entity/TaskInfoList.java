package cn.lhqs.lab.entity;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-12-08 16:16
 * description : TaskInfoList.class
 * version : 1.0
 */
public class TaskInfoList {
    private String taskId;
    private String taskName;
    private List<CommentInfo> list;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<CommentInfo> getList() {
        return list;
    }

    public void setList(List<CommentInfo> list) {
        this.list = list;
    }
}
