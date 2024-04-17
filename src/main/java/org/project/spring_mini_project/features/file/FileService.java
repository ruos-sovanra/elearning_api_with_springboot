package org.project.spring_mini_project.features.file;

import jakarta.servlet.http.HttpServletRequest;
import org.project.spring_mini_project.features.file.dto.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileResponse updateFile(String filename, MultipartFile newFile, HttpServletRequest request);

    FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);

    List<String> uploadMultipleFiles(MultipartFile[] files);

    ResponseEntity<Resource> serveFile(String filename, HttpServletRequest request);

    FileResponse deleteFile(String filename,HttpServletRequest request);

    List<FileResponse> getAllFiles(HttpServletRequest request);
}
