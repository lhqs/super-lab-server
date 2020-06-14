package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.*;
import cn.lhqs.lab.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-07 21:32
 * description : FeedbackController.class
 * version : 1.0
 */

@RestController
@RequestMapping("api")
public class FeedbackController {

    private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Resource
    FeedbackService feedbackServiceImpl;

    @GetMapping("/feed/insertFeedbackMsg")
    public ReturnResult insertFeedbackMsg(@RequestParam("taskId")String taskId, @RequestParam("feedbackMsg") String feedbackMsg) {
        logger.info("taskId:" + taskId + ", feedbackMsg:" + feedbackMsg);
        int respCode = feedbackServiceImpl.updateFeedbackMsg(taskId,feedbackMsg);
        if(respCode != 0) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/feed/uploadFileRecord")
    public ReturnResult uploadFileRecord(@RequestParam("taskId")String taskId, @RequestParam("fileUrl") String fileUrl, @RequestParam("fileName") String fileName) {
        logger.info("taskId:" + taskId + ", fileUrl:" + fileUrl + ", fileName : " + fileName);
        int respCode = feedbackServiceImpl.updateFeedbackFileUrl(taskId,fileUrl, fileName);
        if(respCode != 0) {
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @PostMapping("/feed/addPurchaseSheet")
    public ReturnResult addPurchaseSheet(@RequestBody PurchaseInfo purchaseInfo) {
        logger.info("purchaseInfo -->"+purchaseInfo);
        int respCode = feedbackServiceImpl.insertPurchaseSheet(purchaseInfo);
        if (respCode == 1){
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @PostMapping("/feed/addReimSheet")
    public ReturnResult addReimSheet(@RequestBody ReimInfo reimInfo) {
        logger.info("reimInfo -->"+reimInfo);
        int respCode = feedbackServiceImpl.insertReimSheet(reimInfo);
        if (respCode == 1){
            return ReturnResult.ok;
        }
        return ReturnResult.fail;
    }

    @GetMapping("/feed/getPurchaseList")
    public ReturnResult getPurchaseList(@RequestParam(value = "pageNum",  defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("pageNum -->"+pageNum + ", pageSize -->" + pageSize );
        PageInfo<PurchaseInfo> purchaseInfoPageInfo = feedbackServiceImpl.getPurchaseSheets(pageNum, pageSize);
        return new ReturnResult(200, "success", purchaseInfoPageInfo);
    }

    @GetMapping("/feed/getReimList")
    public ReturnResult getReimList(@RequestParam(value = "pageNum",  defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("pageNum -->"+pageNum + ", pageSize -->" + pageSize );
        PageInfo<ReimInfo> reimInfoPageInfo = feedbackServiceImpl.getReimSheets(pageNum, pageSize);
        return new ReturnResult(200, "success", reimInfoPageInfo);
    }

    @GetMapping("/feed/getFeedbackTaskSheet")
    public ReturnResult getFeedbackTaskSheet(@RequestParam(value = "pageNum",  defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                    @RequestParam("tags")String tags) {
        logger.info("pageNum -->"+pageNum + ", pageSize -->" + pageSize + ", tags --> " + tags  );
        PageInfo<TaskSheet> taskSheetPageInfo = feedbackServiceImpl.getFeedbackTaskList(pageNum, pageSize, tags);
        return new ReturnResult(200, "success", taskSheetPageInfo);
    }


}
