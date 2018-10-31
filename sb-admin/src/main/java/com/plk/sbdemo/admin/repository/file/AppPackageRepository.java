package com.plk.sbdemo.admin.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.admin.domain.file.AppPackage;

public interface AppPackageRepository extends JpaRepository<AppPackage, Long> {

}
