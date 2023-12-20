package tech.divij.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.divij.constants.Constants;
import tech.divij.entity.FileEntity;
import tech.divij.exceptions.IllegalFileTypeException;
import tech.divij.repository.DownloadRepository;

@Service
@Slf4j
public class DownloadService {

  private final DownloadRepository downloadRepository;

  @Autowired
  public DownloadService(DownloadRepository downloadRepository) {
    this.downloadRepository = downloadRepository;
  }

  public ResponseEntity<Resource> getFileById(Integer fileId)
      throws MalformedURLException, FileNotFoundException {

    FileEntity fileEntity = downloadRepository.getReferenceById(fileId);
    String fileExtension = fileEntity.getFileExtension();
    String filePath =
        fileEntity.getFilePath()
            + Constants.FORWARD_SLASH
            + fileEntity.getFileId()
            + Constants.DOT
            + fileEntity.getFileExtension();

    log.info(filePath);

    Path newFilePath = Path.of(filePath);

    Resource resource = new UrlResource(newFilePath.toUri());

    if (resource.exists()) {
      MediaType mediaType = getMediaTypeFromExtension(fileExtension);
      log.info("MediaType is " + mediaType.getType());
      return ResponseEntity.ok().contentType(mediaType).body(resource);
    } else {
      log.info("File not found.");
      throw new FileNotFoundException(Constants.FILE_NOT_FOUND);
    }
  }

  public ResponseEntity<Resource> getFileById(String fileId)
      throws MalformedURLException, FileNotFoundException {

    return getFileById(Integer.parseInt(fileId));
  }

  private MediaType getMediaTypeFromExtension(String fileExtension) {

    MediaType mediaType;
    switch (fileExtension) {
      case "jpg":
      case "jpeg":
        mediaType = MediaType.IMAGE_JPEG;
        break;
      case "gif":
        mediaType = MediaType.IMAGE_GIF;
        break;
      case "png":
        mediaType = MediaType.IMAGE_PNG;
        break;
      case "3gp":
      case "mpeg":
      case "mp4":
      case "zip":
      case "apk":
      case "dmg":
        mediaType = MediaType.APPLICATION_OCTET_STREAM;
        break;
      case "pdf":
        mediaType = MediaType.APPLICATION_PDF;
        break;
      case "txt":
        mediaType = MediaType.TEXT_PLAIN;
        break;
      case "json":
        mediaType = MediaType.APPLICATION_JSON;
        break;
      case "xml":
        mediaType = MediaType.TEXT_XML;
        break;
      default:
        throw new IllegalFileTypeException(Constants.UNKNOWN_FILE_TYPE_MESSAGE);
    }

    return mediaType;
  }
}
