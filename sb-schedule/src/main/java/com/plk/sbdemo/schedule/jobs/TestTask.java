package com.plk.sbdemo.schedule.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Quartz provided by SpringBoot
 */
@Component
public class TestTask {

	/**
	 * run per minute
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void cronTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "---The Cron Task Is Running...");
		doTask();
	}
	
	/**
	 * run one minute after the last time this task to run
	 */
	@Scheduled(fixedRate = 60 * 1000)
	public void fixedRateTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "---The Fixed Rate Task Is Running...");
		doTask();
	}

	/**
	 * run one minute after the last time this task was completed
	 */
	@Scheduled(fixedDelay = 60 * 1000)
	public void fixedDelayTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "---The Fixed Delay Task Is Running...");
		doTask();
	}

	/**
	 * Task Simulation
	 */
	private void doTask() {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
