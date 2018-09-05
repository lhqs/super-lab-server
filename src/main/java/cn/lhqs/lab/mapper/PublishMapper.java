package cn.lhqs.lab.mapper;

import cn.lhqs.lab.entity.Content;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-03 0:13
 * description : PublishMapper.class
 * version : 1.0
 */
@Mapper
public interface PublishMapper {
    @Select(" select u.user_id, c.content_id, u.username, u.avatar, c.content, c.file_url, c.image_url, c.create_time  from content_zone c, user_info u where c.status = 1 and  c.user_id = u.user_id order by create_time desc limit 10")
    List<Content> getContent();

    @Select(" select u.user_id, c.content_id, u.username, u.avatar, c.content, c.file_url, c.image_url, c.create_time  from content_zone c, user_info u where c.status = 1 and  c.user_id = u.user_id and content_id < #{contentId} order by create_time desc limit 10")
    List<Content> getContentByContentId(@Param("contentId") String contentId);

    @Insert(" insert into content_zone(user_id, content, file_url, image_url, create_time) values(#{userId}, #{content}, #{fileUrl}, #{imageUrl}, #{createTime}) ")
    int addContent(@Param("userId") String userId, @Param("content") String content, @Param("fileUrl") String fileUrl,
                   @Param("imageUrl") String imageUrl, @Param("createTime") Date createTime);

    @Update("update content_zone set status = 0  where content_id = #{contentId}")
    int hiddeContent(@Param("contentId") String contentId);
}
