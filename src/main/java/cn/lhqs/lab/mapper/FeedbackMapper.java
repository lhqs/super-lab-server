package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-08 16:33
 * description : FeedbackMapper.class
 * version : 1.0
 */
@Mapper
public interface FeedbackMapper {

    @Update(" update task_list set feedback_message = #{feedbackMsg} where task_id = #{taskId}")
    int modifyFeedbackMsg(@Param("taskId")String taskId, @Param("feedbackMsg")String feedbackMsg);

    @Update(" update task_list set feedback_url = #{feedbackFileUrl}, feedback_filename = #{fileName} where task_id = #{taskId}")
    int updateFeedbackFileUrl(@Param("taskId")String taskId, @Param("feedbackFileUrl")String feedbackFileUrl, @Param("fileName")String fileName);

    @Insert(" insert into purchase_form (user_id, project_illustration, purchase_type, amount, incident, take_over, create_time) values(#{userId}, #{projectIllustration}, #{purchaseType}, #{amount}, #{incident}, #{takeOver},  #{createTime} ) ")
    int addPurchaseSheet(PurchaseInfo purchaseInfo);

    @Insert(" insert into reimbursement_form (user_id, project_illustration, reimbursement_type, amount, incident, take_over, create_time) values(#{userId}, #{projectIllustration}, #{reimbursementType}, #{amount}, #{incident}, #{takeOver},  #{createTime} ) ")
    int addReimSheet(ReimInfo reimInfo);

    @Select("select count(*) from purchase_form ")
    long getPurchaseTotal();

    @Select("select u.username, p.project_illustration, p.purchase_type, p.amount, p.incident, p.take_over, p.create_time from purchase_form p, user_info u where p.user_id = u.user_id order by p.create_time desc limit #{pageNum}, #{pageSize} ")
    List<PurchaseInfo> getPurchaseSheet(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("select u.username, p.project_illustration, p.purchase_type, p.amount, p.incident, p.take_over, p.create_time from purchase_form p, user_info u where p.user_id = u.user_id order by p.create_time desc ")
    List<PurchaseInfo> getAllPurchaseSheet();

    @Select("select count(*) from reimbursement_form ")
    long getReimTotal();

    @Select("select u.username, r.project_illustration, r.reimbursement_type, r.amount, r.incident, r.take_over, r.create_time from reimbursement_form r, user_info u where r.user_id = u.user_id order by r.create_time desc limit #{pageNum}, #{pageSize}")
    List<ReimInfo> getReimSheet(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("select u.username, r.project_illustration, r.reimbursement_type, r.amount, r.incident, r.take_over, r.create_time from reimbursement_form r, user_info u where r.user_id = u.user_id order by r.create_time desc")
    List<ReimInfo> getAllReimSheet();

    @Select({"<script>",
            " SELECT count(*) ",
            "FROM task_list t, user_info u where t.user_id = u.user_id ",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic') ",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "</script>"
    })
    long getFeedbackTaskTotal(@Param("tags")String tags);

    @Select({"<script>",
            " SELECT u.username, t.task_name, t.task_type,t.progress, t.create_time, t.feedback_message, t.feedback_time,t.feedback_filename, t.feedback_url ",
            "FROM task_list t, user_info u where t.user_id = u.user_id ",
            "<when test='tags==\"daily\"'>",
            " AND (t.task_type = 'reimbursement' or t.task_type ='purchase' or t.task_type = 'activity' or t.task_type = 'academic') ",
            "</when>",
            "<when test='tags==\"acedemic\"'>",
            " AND (t.task_type = 'read' or t.task_type ='experiment' or t.task_type = 'write') ",
            "</when>",
            "  order by t.create_time desc limit #{pageNum}, #{pageSize} ",
            "</script>"
    })
    List<TaskSheet> selectFeedbackTaskList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("tags")String tags);




}
