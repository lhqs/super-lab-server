package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.*;
import cn.lhqs.lab.mapper.FeedbackMapper;
import cn.lhqs.lab.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-08 16:34
 * description : FeedbackServiceImpl.class
 * version : 1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @SuppressWarnings("all")
    @Resource
    FeedbackMapper feedbackMapper;

    @Override
    public int updateFeedbackMsg(String taskId, String feedbackMsg) {
        return feedbackMapper.modifyFeedbackMsg(taskId, feedbackMsg);
    }

    @Override
    public int updateFeedbackFileUrl(String taskId, String feedbackFileUrl, String fileName) {
        return feedbackMapper.updateFeedbackFileUrl(taskId,feedbackFileUrl, fileName);
    }

    @Override
    public int insertPurchaseSheet(PurchaseInfo purchaseInfo) {
        return feedbackMapper.addPurchaseSheet(purchaseInfo);
    }

    @Override
    public int insertReimSheet(ReimInfo reimInfo) {
        return feedbackMapper.addReimSheet(reimInfo);
    }

    @Override
    public PageInfo<PurchaseInfo> getPurchaseSheets(int pageNum, int pageSize) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<PurchaseInfo> pageInfo = new PageInfo<>();
        List<PurchaseInfo> purchaseLists = feedbackMapper.getPurchaseSheet(realPageNum, pageSize);
        pageInfo.setTotal(feedbackMapper.getPurchaseTotal());
        pageInfo.setList(purchaseLists);
        return pageInfo;
    }

    @Override
    public PageInfo<ReimInfo> getReimSheets(int pageNum, int pageSize) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<ReimInfo> pageInfo = new PageInfo<>();
        List<ReimInfo> reimLists = feedbackMapper.getReimSheet(realPageNum, pageSize);
        pageInfo.setTotal(feedbackMapper.getReimTotal());
        pageInfo.setList(reimLists);
        return pageInfo;
    }

    @Override
    public PageInfo<TaskSheet> getFeedbackTaskList(int pageNum, int pageSize, String tags) {
        int realPageNum = ( pageNum - 1) * pageSize;
        PageInfo<TaskSheet> pageInfo = new PageInfo<>();
        List<TaskSheet> taskLists = feedbackMapper.selectFeedbackTaskList(realPageNum, pageSize,tags);
        pageInfo.setTotal(feedbackMapper.getFeedbackTaskTotal(tags));
        pageInfo.setList(taskLists);
        return pageInfo;
    }
}
