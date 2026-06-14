package interview_prep_system.service;

import interview_prep_system.dto.JobMatchResponse;
import interview_prep_system.entity.Resume;
import interview_prep_system.repository.ResumeRepository;
import interview_prep_system.util.PdfTextExtractor;
import interview_prep_system.util.SkillDetector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobMatchService {

    private final ResumeRepository resumeRepository;

    public JobMatchService(
            ResumeRepository resumeRepository) {

        this.resumeRepository = resumeRepository;
    }

    public JobMatchResponse matchResumeWithJobDescription(
            Long resumeId,
            String jobDescription)
            throws Exception {

        Resume resume =
                resumeRepository.findById(resumeId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resume not found"));

        String resumeText =
                PdfTextExtractor.extractText(
                        resume.getFilePath());

        List<String> resumeSkills =
                SkillDetector.detectSkills(
                        resumeText);

        List<String> jdSkills =
                SkillDetector.detectSkills(
                        jobDescription);

        List<String> matchedSkills =
                new ArrayList<>();

        List<String> missingSkills =
                new ArrayList<>();

        for (String skill : jdSkills) {

            if (resumeSkills.contains(skill)) {

                matchedSkills.add(skill);

            } else {

                missingSkills.add(skill);
            }
        }

        int matchPercentage = 0;

        if (!jdSkills.isEmpty()) {

            matchPercentage =
                    (matchedSkills.size() * 100)
                            / jdSkills.size();
        }

        List<String> suggestions =
                new ArrayList<>();

        for (String skill : missingSkills) {

            suggestions.add(
                    "Add experience with "
                            + skill);
        }

        return new JobMatchResponse(
                matchPercentage,
                matchedSkills,
                missingSkills,
                suggestions
        );
    }
}