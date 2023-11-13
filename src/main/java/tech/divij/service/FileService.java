package tech.divij.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.divij.response.Response;

@Service
public class FileService {

  private final DownloadService downloadService;

  private final UploadService uploadService;

  public FileService(DownloadService downloadService, UploadService uploadService) {
    this.downloadService = downloadService;
    this.uploadService = uploadService;
  }

  public ResponseEntity<Resource> getFileById(String fileId)
      throws MalformedURLException, FileNotFoundException {

    return downloadService.getFileById(fileId);
  }

  public Response<Object> uploadFile(MultipartFile file) {

    return uploadService.uploadFile(file);
  }
}
