package tech.divij.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.divij.response.Response;
import tech.divij.service.ResumeService;

@Slf4j
@RestController
@RequestMapping("resume")
//@CrossOrigin(
//    origins = "*",
//    maxAge = 3600,
//    methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class ResumeController {

  private final ResumeService resumeService;

  @Autowired
  public ResumeController(ResumeService resumeService) {
    this.resumeService = resumeService;
  }

  @GetMapping("/view")
  public ResponseEntity<Resource> getFile() {

    try {
      return resumeService.getResume();
    } catch (FileNotFoundException e) {
      log.error(e.getMessage());
      ResponseEntity.notFound().build();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    return ResponseEntity.unprocessableEntity().build();
  }

  @PostMapping("/upload")
  @CrossOrigin
  public Response<Object> uploadFile(
      @RequestParam("file") MultipartFile file, @RequestParam("comment") String comment) {

    return resumeService.uploadResume(file, comment);
  }
}
