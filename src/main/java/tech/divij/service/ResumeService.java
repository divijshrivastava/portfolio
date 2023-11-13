package tech.divij.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.divij.dao.FileEntity;
import tech.divij.dao.ResumeEntity;
import tech.divij.repository.ResumeRepository;
import tech.divij.response.Response;

@Service
@Slf4j
public class ResumeService {

  private final FileService fileService;

  private final ResumeRepository resumeDao;

  private final DownloadService downloadService;

  private final UserAuthenticationService userAuthenticationService;

  public ResumeService(
      FileService fileService,
      ResumeRepository resumeDao,
      DownloadService downloadService,
      UserAuthenticationService userAuthenticationService) {
    this.fileService = fileService;
    this.resumeDao = resumeDao;
    this.downloadService = downloadService;
    this.userAuthenticationService = userAuthenticationService;
  }

  public ResponseEntity<Resource> getResume() throws FileNotFoundException, MalformedURLException {
    log.info("ResumeService:getResume()");
    Optional<ResumeEntity> resumeEntity = resumeDao.findTopByOrderBySaveTimeDesc();
    log.info("Resume Entity:{}", resumeEntity.toString());
    ResumeEntity resume;
    if (resumeEntity.isPresent()) {
      resume = resumeEntity.get();
    } else {
      throw new FileNotFoundException();
    }

    return downloadService.getFileById(resume.getFileId().getFileId());
  }

  public Response<Object> uploadResume(MultipartFile file, String comment) {

    if (!userAuthenticationService.isAdmin()) {
      return Response.builder()
          .responseCode("FAILURE")
          .responseMessage("Resume cannot be uploaded as logged in user is not admin.")
          .build();
    }

    Integer fileId = (Integer) fileService.uploadFile(file).getMessage();
    log.info("Resume file uploaded. File id is {}", fileId);

    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileId(fileId);

    ResumeEntity resumeEntity = new ResumeEntity();
    resumeEntity.setFileId(fileEntity);
    resumeEntity.setComment(comment);
    resumeEntity.setStatus("INACTIVE");
    resumeEntity.setSaveTime(LocalDateTime.now());

    resumeDao.save(resumeEntity);

    return Response.builder()
        .message(fileId.toString())
        .responseMessage("Successfully uploaded resume")
        .responseCode("SUCCESS")
        .build();
  }
}
