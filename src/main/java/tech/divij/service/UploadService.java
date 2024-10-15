package tech.divij.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.divij.constants.Constants;
import tech.divij.entity.FileEntity;
import tech.divij.exceptions.FileIsEmpty;
import tech.divij.exceptions.FileTooLargeException;
import tech.divij.exceptions.IllegalFileTypeException;
import tech.divij.repository.UploadRepository;
import tech.divij.response.Response;

@Service
@Slf4j
public class UploadService {

  private final UploadRepository uploadRepository;
  private final UserAuthenticationService userAuthenticationService;
  String empty = "File is empty";
  String successMessage = "File uploaded successfully";
  String failure = "Could not upload file.:";

  @Value("${images.volume.path}")
  private String imagesVolumePath;

  @Value("${video.volume.path}")
  private String videoVolumePath;

  @Value("${pdf.volume.path}")
  private String pdfVolumePath;

  @Value("${archive.volume.path}")
  private String archiveVolumePath;

  @Value("${text.volume.path}")
  private String textVolumePath;

  @Value("${max.file.size}")
  private String maxFileSize;

  public UploadService(
      UploadRepository uploadRepository, UserAuthenticationService userAuthenticationService) {
    this.uploadRepository = uploadRepository;
    this.userAuthenticationService = userAuthenticationService;
  }

  @Transactional(rollbackOn = RuntimeException.class)
  public Response<Object> uploadFile(MultipartFile file)
      throws IllegalFileTypeException, FileIsEmpty, FileTooLargeException {
    log.info("Inside uploadFile()");

    if (!Objects.nonNull(this.userAuthenticationService.getLoggedInUserDetails())) {
      throw new UsernameNotFoundException("User must be logged in to upload file");
    }

    if (file.isEmpty()) {
      log.info(empty);
      throw new FileIsEmpty();
    }

    byte[] bytes;
    try {
      bytes = file.getBytes();
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(Constants.COULD_NOT_READ_FILE_MESSAGE);
    }
    String fileFullName = file.getOriginalFilename();
    long size = file.getSize();
    if (size > Long.parseLong(maxFileSize) * 1024 * 1024) {
      log.error("File too large {}", size);
      throw new FileTooLargeException();
    }
    String fileName =
        Objects.requireNonNull(fileFullName)
            .substring(Constants.ZERO, fileFullName.lastIndexOf(Constants.DOT));
    String fileExtension =
        fileFullName.substring(fileFullName.lastIndexOf(Constants.DOT) + Constants.ONE);

    String fileType = getFileTypeFromExtension(fileExtension);
    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileExtension(fileExtension);
    fileEntity.setOriginalFileName(fileName);
    fileEntity.setFileType(fileType);
    String filePath = getFilePathFromFileType(fileType);
    fileEntity.setFilePath(filePath);
    fileEntity.setFileSize(size);
    fileEntity.setEncrypted(isEncrypted(fileType));
    fileEntity = uploadRepository.save(fileEntity);
    fileEntity.setSaveTime(LocalDateTime.now());
    Path parentPath = Path.of(filePath);
    if (!Files.exists(parentPath)) {
      log.info("Parent Path: {}", parentPath);
      try {
        Files.createDirectories(parentPath);
      } catch (IOException e) {
        log.info(e.getMessage());
        throw new RuntimeException(Constants.COULD_NOT_CREATE_DIRECTORY_MESSAGE);
      }
    }
    Path path =
        Paths.get(
            filePath
                + Constants.FORWARD_SLASH
                + fileEntity.getFileId()
                + "."
                + fileEntity.getFileExtension());
    try {
      Files.write(path, bytes);
    } catch (IOException e) {

      log.info(e.getMessage());
      throw new RuntimeException(Constants.FILE_UPLOAD_ERROR);
    }
    log.info("Uploaded file. File ID: {}", fileEntity.getFileId());
    return Response.builder()
        .message(fileEntity.getFileId())
        .responseMessage(successMessage)
        .responseCode("SUCCESS")
        .build();
  }

  private String isEncrypted(String fileType) {
    if (fileType.equals(Constants.IMAGE)) {
      return Constants.NO;
    }
    return Constants.YES;
  }

  private String getFilePathFromFileType(String fileType) {

    String filePath = null;

    switch (fileType) {
      case Constants.IMAGE:
        filePath = imagesVolumePath;
        break;
      case "VIDEO":
        filePath = videoVolumePath;
        break;
      case "PDF":
        filePath = pdfVolumePath;
        break;
      case "TEXT":
        filePath = textVolumePath;
        break;
      case "ARCHIVE":
        filePath = archiveVolumePath;
        break;
    }
    return filePath;
  }

  private String getFileTypeFromExtension(String fileExtension) {

    String fileType;
    fileExtension = fileExtension.toLowerCase();

    switch (fileExtension) {
      case "jpg":
      case "jpeg":
      case "gif":
      case "png":
      case "heic":
        fileType = Constants.IMAGE;
        break;
      case "mp4":
      case "mpeg":
      case "3gp":
        fileType = "VIDEO";
        break;
      case "pdf":
        fileType = "PDF";
        break;
      case "txt":
        fileType = "TEXT";
        break;
      default:
        throw new IllegalFileTypeException(Constants.UNKNOWN_FILE_TYPE_MESSAGE);
    }

    return fileType;
  }
}
