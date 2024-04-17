package org.project.spring_mini_project.features.file;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.file.dto.FileResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    @Operation(summary = "Upload single file")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<FileResponse> uploadSingleFile(
            @RequestPart("file") MultipartFile file, HttpServletRequest request
    ) {
        return BaseResponse
                .<FileResponse>createSuccess()
                .setPayload(fileService.uploadSingleFile(file, request));
    }

    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    @Operation(summary = "Upload multiple files")
    public BaseResponse<List<String>> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {
        return BaseResponse
                .<List<String>>createSuccess()
                .setPayload(fileService.uploadMultipleFiles(files));
    }

    @GetMapping("/download/{fileName}")
    @Operation(summary = "Download file")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        return fileService.serveFile(fileName,request);
    }

    @DeleteMapping("{fileName}")
    @Operation(summary = "Delete file")
    public BaseResponse<FileResponse> deleteFile(@PathVariable String fileName, HttpServletRequest request) {
        return BaseResponse
                .<FileResponse>createSuccess()
                .setPayload(fileService.deleteFile(fileName, request));
    }

    @GetMapping
    @Operation(summary = "Get all files")
    public BaseResponse<List<FileResponse>> getAllFiles(HttpServletRequest request) {
        return BaseResponse
                .<List<FileResponse>>createSuccess()
                .setPayload(fileService.getAllFiles(request));
    }

    @PutMapping(value = "/{filename}", consumes = "multipart/form-data")
    @Operation(summary = "Update file")
    public BaseResponse<FileResponse> updateFile(
            @PathVariable String filename,
            @RequestPart("file") MultipartFile newFile,
            HttpServletRequest request
    ) {
        return BaseResponse
                .<FileResponse>createSuccess()
                .setPayload(fileService.updateFile(filename, newFile, request));
    }


}
