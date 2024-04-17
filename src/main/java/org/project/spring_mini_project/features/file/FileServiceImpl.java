package org.project.spring_mini_project.features.file;

import jakarta.servlet.http.HttpServletRequest;
import org.project.spring_mini_project.features.file.dto.FileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

@Service

public class FileServiceImpl implements FileService {
    @Value("${file_storage.image_location}")
    String fileStorageDir;

    private static final Set<String> SUPPORTED_IMAGE_TYPES = Set.of(
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    );

    private String generateImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/images/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    private String generateDownloadImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/api/v1/files/download/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    private String uploadFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (!SUPPORTED_IMAGE_TYPES.contains(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE, contentType + " is not supported"
            );
        }
        try {
//       Check if the directory doesn't exist , we will create the directory
            Path fileStoragePath = Path.of(fileStorageDir);
            if (!Files.exists(fileStoragePath)) {
                Files.createDirectories(fileStoragePath);
            }
            String fileName = UUID.randomUUID() + "." +
                    Objects.requireNonNull(file.getOriginalFilename())
                            .split("\\.")[1];
            // handle if there are more than one dot !

            Files.copy(file.getInputStream(),
                    fileStoragePath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request) {

        String filename = uploadFile(file);
        String fullImageUrl = generateImageUrl(request, filename);

        return FileResponse.builder()
                .downloadUrl(generateDownloadImageUrl(request, filename))
                .fileType(file.getContentType())
                .size((float) file.getSize() / 1024) // in KB
                .filename(filename)
                .fullUrl(fullImageUrl).build();
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        var fileNames = new ArrayList<String>();
        for (var file : files) {
            fileNames.add(uploadFile(file));
        }
        return fileNames;
    }

    @Override
    public ResponseEntity<Resource> serveFile(String filename, HttpServletRequest request) {
        try {
            //        get path of the images
            Path imagePath = Path.of(fileStorageDir).resolve(filename);
            Resource resourceUrl = new UrlResource(imagePath.toUri());
            if (resourceUrl.exists()) {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.parseMediaType("image/jpeg"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceUrl.getFilename() + "\"")
                        .body(resourceUrl);
            } else {
                // bad request
                throw new RuntimeException("Resources not found ! ");
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public FileResponse deleteFile(String filename, HttpServletRequest request) {
        try {
            Path filePath = Path.of(fileStorageDir).resolve(filename);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to delete the file: " + filename);
        }
        return FileResponse.builder()
                .filename(filename)
                .build();
    }



    @Override
    public List<FileResponse> getAllFiles(HttpServletRequest request) {
        List<FileResponse> fileResponses = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(fileStorageDir))) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        String filename = file.getFileName().toString();
                        String fullImageUrl = generateImageUrl(request, filename);
                        FileResponse fileResponse = FileResponse.builder()
                                .downloadUrl(generateDownloadImageUrl(request, filename))
                                .filename(filename)
                                .fullUrl(fullImageUrl).build();
                        fileResponses.add(fileResponse);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileResponses;
    }

    @Override
    public FileResponse updateFile(String filename, MultipartFile newFile, HttpServletRequest request) {
        // Delete the old file
        deleteFile(filename,request);
        // Upload the new file with the same filename
        String newFilename = uploadFile(newFile);
        String fullImageUrl = generateImageUrl(request, newFilename);

        return FileResponse.builder()
                .downloadUrl(generateDownloadImageUrl(request, newFilename))
                .fileType(newFile.getContentType())
                .size((float) newFile.getSize() / 1024) // in KB
                .filename(newFilename)
                .fullUrl(fullImageUrl).build();
    }
}
