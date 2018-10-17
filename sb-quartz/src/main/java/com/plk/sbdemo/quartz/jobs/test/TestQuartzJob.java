package com.plk.sbdemo.quartz.jobs.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.plk.sbdemo.quartz.jobs.QuartzJob;
import com.plk.sbdemo.quartz.jobs.annotation.JobConfig;

/**
 * 定时任务二
 */
@Component("TestForQuartzJob")
@JobConfig(group="Test", name="TestForQuartzJob", cron="0 0/10 * * * ?", desc="定时任务二")
public class TestQuartzJob extends QuartzJob {

	@Override
	public void executeInternal(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "---A Job Is Running...");
	}
}
