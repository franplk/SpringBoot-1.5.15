package com.plk.sbdemo.admin.service.file;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.domain.file.AppPackage;
import com.plk.sbdemo.admin.repository.file.AppPackageRepository;

@Service
public class AppPackageService {

	@Autowired
	private AppPackageRepository packRepository;
	
	public AppPackage addPackage(AppPackage pack) {
		pack.setUploadTime(new Date());
		return packRepository.save(pack);
	}
	
	public List<AppPackage> packList() {
		return packRepository.findAll();
	}
}
