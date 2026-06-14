package interview_prep_system.service;

import interview_prep_system.dto.InterviewQuestion;
import interview_prep_system.entity.Resume;
import interview_prep_system.repository.ResumeRepository;
import interview_prep_system.util.PdfTextExtractor;
import interview_prep_system.util.SkillDetector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobInterviewQuestionService {

    private final ResumeRepository resumeRepository;

    private final InterviewQuestionService interviewQuestionService;

    public JobInterviewQuestionService(
            ResumeRepository resumeRepository,
            InterviewQuestionService interviewQuestionService) {

        this.resumeRepository = resumeRepository;
        this.interviewQuestionService =
                interviewQuestionService;
    }

    public List<InterviewQuestion> generateQuestions(
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

        Set<String> combinedSkills =
                new HashSet<>();

        combinedSkills.addAll(resumeSkills);

        combinedSkills.addAll(jdSkills);

        return interviewQuestionService
                .generateQuestionsFromSkills(
                        new ArrayList<>(combinedSkills));
    }
}