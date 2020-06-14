package cn.lhqs.lab;

import cn.lhqs.lab.entity.PageInfo;
import cn.lhqs.lab.entity.ReimInfo;
import cn.lhqs.lab.service.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabApplicationTests {
	@Resource
	FeedbackService feedbackServiceImpl;

	@Test
	public void contextLoads() {
		PageInfo<ReimInfo> reimInfoPageInfo = feedbackServiceImpl.getReimSheets(1, 5);
		System.out.println(reimInfoPageInfo);
	}

}
