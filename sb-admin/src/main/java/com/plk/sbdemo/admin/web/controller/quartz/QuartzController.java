package com.plk.sbdemo.admin.web.controller.quartz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quartz")
public class QuartzController {

	@RequestMapping("")
	public String index() {
		return "quartz/jobList";
	}
	
	@RequestMapping("/joblist")
	public String jobListPage() {
		return "quartz/jobList";
	}
}
