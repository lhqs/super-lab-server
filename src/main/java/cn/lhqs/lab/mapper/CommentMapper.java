package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.CommentInfo;
import cn.lhqs.lab.entity.PublishTalkVO;
import cn.lhqs.lab.entity.TaskList;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-12-08 13:41
 * description : CommentMapper.class
 * version : 1.0
 */
@Mapper
public interface CommentMapper {
    // @Select("SELECT t.task_id, t.user_id, t.task_name from task_list t, user_info u where u.user_id = t.user_id and (t.user_id = #{userId} or u.user_type != 1    )and t.is_verify = 1 and t.status = 1 and t.comment_status = 1 ")
    @Select("SELECT task_id, user_id, task_name from task_list where user_id = #{userId} and is_verify = 1 and status = 1 and comment_status = 1 ; ")
    List<TaskList> selectTaskList(@Param("userId")String userId);

    @Select("SELECT task_id, user_id, task_name from task_list where is_verify = 1 and status = 1 and comment_status = 1 ; ")
    List<TaskList> selectAllTaskList();


    @Select("SELECT DISTINCT  u.user_id, u.username, u.avatar, c.comment_id, c.content, c.parent_id, c.parent_name, c.create_time \n" +
            "FROM comment_info c, user_info u, task_list t WHERE c.user_id = u.user_id AND t.task_name = c.task_name and c.status = 1 and c.task_name = #{taskName}")
    List<CommentInfo> selectCommentByTaskName(@Param("taskName")String taskName);

    @Insert(" insert into comment_info (task_name, user_id, content, parent_id, parent_name, create_time) values(#{taskName}, #{userId}, #{content}, #{parentId}, #{parentName},#{createTime})")
    int insertComment(PublishTalkVO publishTalkVO);

    @Update("update comment_info set status = 0 where task_name = #{taskName}")
    int updateTalkStatus(@Param("taskName")String taskName);

    @Update("update task_list set comment_status = 0 where task_name = #{taskName}")
    int updateCommentStatus(@Param("taskName")String taskName);

    @Select("SELECT user_type FROM user_info where user_id = #{userId}")
    String selectUserType(@Param("userId")String userId);
}
