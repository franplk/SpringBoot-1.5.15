package com.plk.sbdemo.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.file.model.FileModel;

public interface FileRepository extends JpaRepository<FileModel, Long> {

	FileModel findByFileMd5(String fileMd5);

}
