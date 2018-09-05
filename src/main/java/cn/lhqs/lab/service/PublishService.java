package cn.lhqs.lab.service;

import cn.lhqs.lab.entity.Content;
import cn.lhqs.lab.entity.ContentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-03 0:43
 * description : PublishService.class
 * version : 1.0
 */
public interface PublishService {

    int insertContent(ContentVO contentVO);

    List<ContentVO> getContent();

    List<ContentVO> getContent(String contentId);

    int hiddeContent(String contentId);
}
