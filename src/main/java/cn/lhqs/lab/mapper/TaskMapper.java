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

    @Select({"<script>",
            " SELECT count(*) ",
            "FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 1 ",
            "<when test='userType ==\"1\" or userType == \"4\"'>",
            " AND u.user_id = #{userId}",
            "</when>",
            "<when test='userType ==\"3\"'>",
            " AND (u.user_group = 1 or u.user_type = 2)",
            "</when>",
            "<when test='taskConsumer!=\"\"'>",
            " AND u.username = #{taskConsumer}",
            "</when>",
            "<when test='taskProducer!=\"\"'>",
            " AND t.producer = #{taskProducer}",
            "</when>",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic' )",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "<when test='tags==\"project\"'>",
            " AND (t.task_type = 'code' or t.task_type ='test') ",
            "</when>",
            "</script>"
    })
    long total(@Param("taskConsumer") String taskConsumer, @Param("taskProducer")String taskProducer,
               @Param("tags")String tags,@Param("userType")String userType, @Param("userId")String userId);

    @Select({"<script>",
            " SELECT u.username, t.task_id, t.task_name, t.task_type, t.task_describe,t.is_verify,t.progress, t.start_date,t.end_date,t.producer,t.create_time,t.is_emergency ",
            "FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 1 ",
            "<when test='userType ==\"1\" or userType == \"4\"'>",
            " AND u.user_id = #{userId}",
            "</when>",
            "<when test='userType ==\"3\"'>",
            " AND (u.user_group = 1 or u.user_type = 2)",
            "</when>",
            "<when test='taskConsumer!=\"\"'>",
            " AND u.username = #{taskConsumer}",
            "</when>",
            "<when test='taskProducer!=\"\"'>",
            " AND t.producer = #{taskProducer}",
            "</when>",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic' )",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "<when test='tags==\"project\"'>",
            " AND (t.task_type = 'code' or t.task_type ='test') ",
            "</when>",
            "  order by create_time desc limit #{pageNum}, #{pageSize} ",
            "</script>"
    })
    List<TaskList> selectTaskList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("taskConsumer") String taskConsumer, @Param("taskProducer")String taskProducer,
                                  @Param("tags")String tags,@Param("userType")String userType, @Param("userId")String userId);

    @Select({"<script>",
            " SELECT count(*)",
            "FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 0 ",
            "<when test='userType ==\"1\" or userType == \"4\"'>",
            " AND u.user_id = #{userId}",
            "</when>",
            "<when test='userType ==\"3\"'>",
            " AND (u.user_group = 1 or u.user_type = 2)",
            "</when>",
            "<when test='taskConsumer!=\"\"'>",
            " AND u.username = #{taskConsumer}",
            "</when>",
            "<when test='taskProducer!=\"\"'>",
            " AND t.producer = #{taskProducer}",
            "</when>",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic') ",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "<when test='tags==\"project\"'>",
            " AND (t.task_type = 'code' or t.task_type ='test') ",
            "</when>",
            "</script>"
    })
    long totaled(@Param("taskConsumer") String taskConsumer, @Param("taskProducer")String taskProducer,
                 @Param("tags")String tags,@Param("userType")String userType, @Param("userId")String userId);

    @Insert({ "<script>",
            " insert into task_list (user_id, task_name, task_type, task_describe, start_date, end_date,producer, create_time, address, budget, is_emergency) ",
            " values(#{userId}, #{taskName}, #{taskType}, #{taskDescribe}, #{startDate},#{endDate}, #{producer}, #{createTime},#{address}, #{budget}, #{isEmergency} ",
            // "<when test='address!=null'>",
            // " #{address},",
            // "</when>",
            // "<when test='budget!=null'>",
            // "#{budget}",
            // "</when>",
            " ) ",
            "</script>"
    })
    int insertTaskList(@Param("taskName") String taskName, @Param("taskType") String taskType,
                       @Param("taskDescribe") String taskDescribe, @Param("startDate") Date startDate,
                       @Param("endDate")Date endDate, @Param("userId") String userId,
                       @Param("producer") String producer, @Param("createTime") Date createTime,
                       @Param("address")String address, @Param("budget")double budget,
                        @Param("isEmergency")int isEmergency);

    // @Select(" SELECT u.username, t.task_id, t.task_name, t.task_type, t.task_describe,t.is_verify,t.progress, t.start_date,t.end_date,t.producer,t.create_time " +
    //         "FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 1  order by create_time desc limit #{pageNum}, #{pageSize} ")
    // List<TaskList> selectTaskList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("taskConsumer") String taskConsumer, @Param("taskProducer")String taskProducer);



    @Select({"<script>",
            " SELECT u.username, t.task_id, t.task_name, t.task_type, t.task_describe,t.is_verify,t.progress, t.start_date,t.end_date,t.producer,t.is_emergency, t.create_time ",
            "FROM task_list t, user_info u where t.user_id = u.user_id and t.status = 0 ",
            "<when test='userType ==\"1\" or userType == \"4\"'>",
            " AND u.user_id = #{userId}",
            "</when>",
            "<when test='userType ==\"3\"'>",
            " AND (u.user_group = 1 or u.user_type = 2)",
            "</when>",
            "<when test='taskConsumer!=\"\"'>",
            " AND u.username = #{taskConsumer}",
            "</when>",
            "<when test='taskProducer!=\"\"'>",
            " AND t.producer = #{taskProducer}",
            "</when>",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic') ",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "<when test='tags==\"project\"'>",
            " AND (t.task_type = 'code' or t.task_type ='test') ",
            "</when>",
            "  order by create_time desc limit #{pageNum}, #{pageSize} ",
            "</script>"
    })
    List<TaskList> selectCompleteTaskList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("taskConsumer") String taskConsumer, @Param("taskProducer")String taskProducer,
                                          @Param("tags")String tags,@Param("userType")String userType, @Param("userId")String userId);


    @Select(" select t.task_id, t.task_name, t.task_type, t.task_describe, t.progress ,t.start_date, t.end_date, t.create_time, t.address, t.budget, t.feedback_message from  task_list t where t.is_verify = 1 and status = 1  and t.user_id = #{userId} order by t.create_time desc ")
    List<TaskList> selectTaskByUserId(@Param("userId")String userId);

    @Update(" update task_list set is_verify = 1 where task_id = #{taskId}")
    int updateVerify(@Param("taskId")String taskId);

    @Update(" update task_list set status = 0 where task_id = #{taskId}")
    int deleteTaskById(@Param("taskId")String taskId);

    @Update(" update task_list set progress = #{progress} where task_id = #{taskId}")
    int updateProgressById(@Param("taskId")String taskId, @Param("progress") int progress);

    @Select("select count(*) from task_list where is_read = 0 and status = 1 and user_id = #{userId}")
    int getUnreadMsg(@Param("userId")String userId);

    @Select("select task_id, task_name, task_type, is_emergency, create_time from task_list where is_read = 0 and status = 1 and user_id = #{userId} ORDER BY create_time DESC")
    List<TaskList> getUnreadMsgList(@Param("userId")String userId);

    @Update(" update task_list set is_verify = 1, is_read = 1 where task_id = #{taskId}")
    int updateReadState(@Param("taskId")String taskId);

    @Select(" select user_type from user_info where user_id = #{userId}")
    String getUserType(@Param("userId")String userId);
}
