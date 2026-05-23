package interview_prep_system.service;

import interview_prep_system.dto.ResumeUploadResponse;
import interview_prep_system.entity.Resume;
import interview_prep_system.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public ResumeUploadResponse uploadResume(
            MultipartFile file) throws Exception {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath =
                uploadPath.resolve(file.getOriginalFilename());

        Files.copy(
                file.getInputStream(),
                filePath);

        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath.toString());
        resume.setUploadedAt(LocalDateTime.now());

        Resume savedResume =
                resumeRepository.save(resume);

        return new ResumeUploadResponse(
                savedResume.getId(),
                savedResume.getFileName(),
                "Uploaded Successfully");
    }
}