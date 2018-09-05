package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.Content;
import cn.lhqs.lab.entity.ContentVO;
import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-02 23:00
 * description : PublishController.class
 * version : 1.0
 */
@RestController
@RequestMapping("api")
public class PublishController {

    @Resource
    PublishService publishServiceImpl;

    private static Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @PostMapping("/content/addContent")
    public ReturnResult addContent(@RequestBody ContentVO contentVO, HttpServletRequest request, HttpServletResponse response) {
        logger.info("contentVO:" + contentVO);
        int respCode = publishServiceImpl.insertContent(contentVO);
        if( respCode != 0 ){
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/content/getContentList")
    public ReturnResult getContent(@RequestParam("contentId") String contentId) {
        logger.info("more-contentId --> " + contentId);
        List<ContentVO> contentList = null;
        if(contentId == "") {
            contentList = publishServiceImpl.getContent();
        } else {
            contentList = publishServiceImpl.getContent(contentId);
        }
        return new ReturnResult(2000, "success", contentList);
    }

    @GetMapping("/content/hiddeContent")
    public ReturnResult hiddeContent(@RequestParam("contentId") String contentId) {
        logger.info("contentId --> " + contentId);
        int respCode = publishServiceImpl.hiddeContent(contentId);
        if (respCode != 0 ) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

}
