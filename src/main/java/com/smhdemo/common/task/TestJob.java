package com.smhdemo.common.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Component
public class TestJob {
    @Scheduled(cron = "0 30 9 ? * *")//每天9：30执行一次   
	public void test1() {
		System.out.println("job1 开始执行");
	}

	@Scheduled(cron = "0/30 * * * * ?")// 每隔30秒执行一次
	public void test2() {
		System.out.println("job2 开始执行");
	}
}

