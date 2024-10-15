package tech.divij.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.divij.response.BlogUploadResponse;
import tech.divij.response.Response;
import tech.divij.service.FileService;

@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

  private final FileService fileService;

  @Autowired
  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping("{fileId}")
  public ResponseEntity<Resource> getFile(@PathVariable("fileId") String fileId) {

    try {
      return fileService.getFileById(fileId);
    } catch (MalformedURLException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    } catch (FileNotFoundException | EntityNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/create")
  @CrossOrigin
  public Response<Object> uploadFile(@RequestParam("file") MultipartFile file) {

    return fileService.uploadFile(file);
  }

  @PostMapping("/blog-image-upload")
  @CrossOrigin
  public BlogUploadResponse uploadBlogFile(@RequestParam("file") MultipartFile file) {
    String url = "/file/" + fileService.uploadFile(file).getMessage();
    return new BlogUploadResponse(url);
  }
}
