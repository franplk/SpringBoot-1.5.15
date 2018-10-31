package com.plk.sbdemo.admin.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.admin.domain.file.ChunkFile;

public interface FileRepository extends JpaRepository<ChunkFile, Long> {

	ChunkFile findByFileMd5(String fileMd5);

}
