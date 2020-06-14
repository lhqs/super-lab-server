package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.Content;
import cn.lhqs.lab.entity.ContentVO;
import cn.lhqs.lab.mapper.PublishMapper;
import cn.lhqs.lab.service.PublishService;
import cn.lhqs.lab.utils.StringExange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-03 0:45
 * description : PublishServiceImp.class
 * version : 1.0
 */
@Service
public class PublishServiceImpl implements PublishService {
    @SuppressWarnings("all")
    @Resource
    PublishMapper publishMapper;

    @Override
    public int insertContent(ContentVO contentVO) {
        String fileUrls = StringExange.arrayToString(contentVO.getFileUrl());
        String imageUrls = StringExange.arrayToString(contentVO.getImageUrl());
        Date currnetTime = new Date();
        return publishMapper.addContent(contentVO.getUserId(), contentVO.getContent(), fileUrls, imageUrls, currnetTime);
    }

    @Override
    public List<ContentVO> getContent() {
        List<Content> contentList = publishMapper.getContent();
        List<ContentVO> contentVOs = new ArrayList<>();
        for( Content content : contentList) {
            ContentVO contentVO = new ContentVO();
            contentVO.setUserId(content.getUserId());
            contentVO.setContentId(content.getContentId());
            contentVO.setUsername(content.getUsername());
            contentVO.setAvatar(content.getAvatar());
            contentVO.setContent(content.getContent());
            contentVO.setFileUrl(content.getFileUrl().split(","));
            contentVO.setImageUrl(content.getImageUrl().split(","));
            contentVO.setCreateTime(content.getCreateTime());
            contentVOs.add(contentVO);
        }
        return contentVOs;
    }

    @Override
    public List<ContentVO> getContent(String contentId) {
        List<Content> contentList = publishMapper.getContentByContentId(contentId);
        List<ContentVO> contentVOs = new ArrayList<>();
        for( Content content : contentList) {
            ContentVO contentVO = new ContentVO();
            contentVO.setUserId(content.getUserId());
            contentVO.setContentId(content.getContentId());
            contentVO.setUsername(content.getUsername());
            contentVO.setAvatar(content.getAvatar());
            contentVO.setContent(content.getContent());
            contentVO.setFileUrl(content.getFileUrl().split(","));
            contentVO.setImageUrl(content.getImageUrl().split(","));
            contentVO.setCreateTime(content.getCreateTime());
            contentVOs.add(contentVO);
        }
        return contentVOs;
    }

    @Override
    public int hiddeContent(String contentId) {
        return publishMapper.hiddeContent(contentId);
    }
}
