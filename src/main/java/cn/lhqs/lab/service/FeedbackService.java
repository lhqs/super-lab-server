package cn.lhqs.lab.service;

import cn.lhqs.lab.entity.*;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-08 16:33
 * description : FeedbackService.class
 * version : 1.0
 */
public interface FeedbackService {

    int updateFeedbackMsg(String taskId, String feedbackMsg);

    int updateFeedbackFileUrl(String taskId, String feedbackFileUrl, String fileName);

    int insertPurchaseSheet(PurchaseInfo purchaseInfo);

    int insertReimSheet(ReimInfo reimInfo);

    PageInfo<PurchaseInfo> getPurchaseSheets(int pageNum, int pageSize);

    PageInfo<ReimInfo> getReimSheets(int pageNum, int pageSize);

    PageInfo<TaskSheet> getFeedbackTaskList(int pageNum, int pageSize, String tags);
}
