package tech.divij.response;

public class BlogUploadResponse {
  private String url;

  public BlogUploadResponse(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
