package com.plk.sbdemo.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring提供的定时任务方式
 * 方便实现，不方便动态管理
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}
}
