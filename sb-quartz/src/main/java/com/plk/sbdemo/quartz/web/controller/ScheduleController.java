package com.plk.sbdemo.quartz.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plk.sbdemo.quartz.domain.JobInfo;
import com.plk.sbdemo.quartz.service.ScheduleService;
import com.plk.sbdemo.quartz.web.ApiResponse;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/list")
	public ApiResponse jobList() throws Exception {
		Map<String, List<JobInfo>> jobMap = scheduleService.jobList();
		List<JobInfo> allJobs = new ArrayList<>();
		for (Entry<String, List<JobInfo>> entry : jobMap.entrySet()) {
			allJobs.addAll(entry.getValue());
		}
		return ApiResponse.success(allJobs);
	}
	
	@PostMapping("/launch")
	public ApiResponse launchJob(String jobName, String jobGroup) {
		scheduleService.resume(jobName, jobGroup);
		return ApiResponse.success();
	}
	
	@PostMapping("/trigger")
	public ApiResponse triggerJob(String jobName, String jobGroup) {
		scheduleService.triggerJob(jobName, jobGroup);
		return ApiResponse.success();
	}
	
	@PostMapping("/cease")
	public ApiResponse ceaseJob(String jobName, String jobGroup) {
		scheduleService.cease(jobName, jobGroup);
		return ApiResponse.success();
	}
}
