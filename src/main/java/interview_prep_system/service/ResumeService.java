package interview_prep_system.service;

import interview_prep_system.dto.ResumeListResponse;
import interview_prep_system.dto.ResumeUploadResponse;
import interview_prep_system.entity.Resume;
import interview_prep_system.repository.ResumeRepository;
import interview_prep_system.util.PdfTextExtractor;
import interview_prep_system.util.SkillDetector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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
                filePath,
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath.toString());
        resume.setUploadedAt(LocalDateTime.now());

        Resume savedResume =
                resumeRepository.save(resume);

        String resumeText =
                PdfTextExtractor.extractText(
                        filePath.toString());

        System.out.println(
                "===== RESUME TEXT =====");

        System.out.println(
                resumeText);

        List<String> matchedSkills =
                SkillDetector.detectSkills(
                        resumeText);

        System.out.println(
                "Detected Skills: "
                        + matchedSkills);

        int totalSkills =
                SkillDetector
                        .getAllSkills()
                        .size();

        int matchedSkillCount =
                matchedSkills.size();

        int atsScore =
                (matchedSkillCount * 100)
                        / totalSkills;

        String rating;

        if (atsScore >= 80) {

            rating = "Excellent";

        } else if (atsScore >= 60) {

            rating = "Good";

        } else {

            rating = "Needs Improvement";
        }

        List<String> missingSkills =
                SkillDetector
                        .getAllSkills()
                        .stream()
                        .filter(skill ->
                                !matchedSkills.contains(skill))
                        .toList();

        List<String> suggestions =
                missingSkills.stream()
                        .map(skill ->
                                "Consider learning " + skill)
                        .limit(5)
                        .toList();

        return new ResumeUploadResponse(

                savedResume.getId(),

                savedResume.getFileName(),

                "Uploaded Successfully",

                atsScore,

                rating,

                matchedSkills,

                missingSkills,

                suggestions
        );
    }

    public String extractText(Long resumeId) throws Exception {

        Resume resume = resumeRepository
                .findById(resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        File pdfFile = new File(resume.getFilePath());

        PDDocument document = Loader.loadPDF(pdfFile);

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document)
                .replaceAll("\\s+", " ")
                .trim();

        document.close();

        return text;
    }
    public List<ResumeListResponse> getAllResumes() {

        return resumeRepository
                .findAll()
                .stream()
                .map(resume ->
                        new ResumeListResponse(
                                resume.getId(),
                                resume.getFileName(),
                                resume.getUploadedAt()
                        ))
                .toList();
    }
}