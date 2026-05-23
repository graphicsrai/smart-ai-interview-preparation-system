package interview_prep_system.dto;

public class ResumeUploadResponse {

    private Long id;
    private String fileName;
    private String status;

    public ResumeUploadResponse(
            Long id,
            String fileName,
            String status) {

        this.id = id;
        this.fileName = fileName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStatus() {
        return status;
    }
}