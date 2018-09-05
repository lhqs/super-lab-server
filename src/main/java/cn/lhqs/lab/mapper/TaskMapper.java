package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.TaskList;
import cn.lhqs.lab.entity.TaskListVO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-05 1:06
 * description : TaskMapper.class
 * version : 1.0
 */
@Mapper
public interface TaskMapper {

    @Select("select count(*) from task_list where status = 1")
    long total();

    @Insert(" insert into task_list (user_id, task_name, task_type, task_describe, start_date, end_date,producer, create_time) values(#{userId}, #{taskName}, #{taskType}, #{taskDescribe}, #{startDate},#{endDate}, #{producer}, #{createTime} ) ")
    int insertTaskList(@Param("taskName") String taskName, @Param("taskType") String taskType, @Param("taskDescribe") String taskDescribe, @Param("startDate") Date startDate, @Param("endDate")Date endDate, @Param("userId") String userId, @Param("producer") String producer, @Param("createTime") Date createTime);

    @Select(" SELECT u.username, t.task_id, t.task_name, t.task_type, t.task_describe,t.is_verify,t.progress, t.start_date,t.end_date,t.producer,t.create_time FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 1 order by create_time desc limit #{pageNum}, #{pageSize} ")
    List<TaskList> selectTaskList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select(" select t.task_id, t.task_name, t.task_type, t.task_describe, t.progress ,t.start_date, t.end_date, t.create_time from  task_list t where t.is_verify = 1 and status = 1  and t.user_id = #{userId} ")
    List<TaskList> selectTaskByUserId(@Param("userId")String userId);

    @Update(" update task_list set is_verify = 1 where task_id = #{taskId}")
    int updateVerify(@Param("taskId")String taskId);

    @Update(" update task_list set status = 0 where task_id = #{taskId}")
    int deleteTaskById(@Param("taskId")String taskId);

    @Update(" update task_list set progress = #{progress} where task_id = #{taskId}")
    int updateProgressById(@Param("taskId")String taskId, @Param("progress") int progress);

}
