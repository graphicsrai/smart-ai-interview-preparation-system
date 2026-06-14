package interview_prep_system.dto;

import java.time.LocalDateTime;

public class ResumeListResponse {

    private Long id;

    private String fileName;

    private LocalDateTime uploadedAt;

    public ResumeListResponse(
            Long id,
            String fileName,
            LocalDateTime uploadedAt) {

        this.id = id;
        this.fileName = fileName;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}