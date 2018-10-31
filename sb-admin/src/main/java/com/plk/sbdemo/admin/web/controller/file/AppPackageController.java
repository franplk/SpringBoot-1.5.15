package com.plk.sbdemo.admin.web.controller.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.domain.file.AppPackage;
import com.plk.sbdemo.admin.service.file.AppPackageService;
import com.plk.sbdemo.admin.web.ApiResponse;

@Controller
@RequestMapping("/app/package")
public class AppPackageController {

	@Autowired
	private AppPackageService packService;
	
	@GetMapping("")
	public String packPage() {
		return "package/list";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public ApiResponse packList() {
		List<AppPackage> packList = packService.packList();
		
		return ApiResponse.success(packList);
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "package/upload";
	}
	
	@ResponseBody
	@PostMapping("/upload")
	public ApiResponse upload(AppPackage pack) {
		AppPackage newPack = packService.addPackage(pack);		
		return ApiResponse.success(newPack);
	}
}
