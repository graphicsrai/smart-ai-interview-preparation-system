package interview_prep_system.controller;

import interview_prep_system.dto.ResumeUploadResponse;
import interview_prep_system.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import interview_prep_system.dto.ResumeTextResponse;
import interview_prep_system.dto.SkillResponse;
import interview_prep_system.service.SkillService;
import interview_prep_system.dto.AtsScoreResponse;
import interview_prep_system.service.AtsService;
import interview_prep_system.dto.ResumeRecommendationResponse;
import interview_prep_system.service.ResumeRecommendationService;
import interview_prep_system.dto.InterviewQuestionResponse;
import interview_prep_system.service.InterviewQuestionService;
import interview_prep_system.dto.InterviewQuestion;

import interview_prep_system.dto.AnswerEvaluationRequest;
import interview_prep_system.dto.AnswerEvaluationResponse;
import interview_prep_system.service.AnswerEvaluationService;
@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final ResumeService resumeService;
    private final SkillService skillService;

    private final AtsService atsService;
    private final ResumeRecommendationService recommendationService;
    private final InterviewQuestionService interviewQuestionService;
    private final AnswerEvaluationService answerEvaluationService;
    public ResumeController(
            ResumeService resumeService,
            SkillService skillService,
            AtsService atsService,
            ResumeRecommendationService recommendationService,
            InterviewQuestionService interviewQuestionService,
            AnswerEvaluationService answerEvaluationService) {

        this.resumeService = resumeService;
        this.skillService = skillService;
        this.atsService = atsService;
        this.recommendationService = recommendationService;
        this.interviewQuestionService = interviewQuestionService;
        this.answerEvaluationService = answerEvaluationService;
    }

    @PostMapping("/upload")
    public ResumeUploadResponse uploadResume(
            @RequestParam("file")
            MultipartFile file) throws Exception {

        return resumeService.uploadResume(file);
    }

    @GetMapping("/{id}/text")
    public ResumeTextResponse extractText(
            @PathVariable Long id)
            throws Exception {

        String text = resumeService.extractText(id);

        return new ResumeTextResponse(text);
    }

    @GetMapping("/{id}/skills")
    public SkillResponse extractSkills(
            @PathVariable Long id)
            throws Exception {

        String resumeText =
                resumeService.extractText(id);

        return new SkillResponse(
                skillService.extractSkillsFromResume(
                        resumeText));
    }

    @GetMapping("/{id}/ats-score")
    public AtsScoreResponse getAtsScore(
            @PathVariable Long id)
            throws Exception {

        String resumeText =
                resumeService.extractText(id);

        var skills =
                skillService.extractSkillsFromResume(
                        resumeText);

        return atsService.calculateScore(
                resumeText,
                skills);
    }

    @GetMapping("/{id}/recommendations")
    public ResumeRecommendationResponse getRecommendations(
            @PathVariable Long id)
            throws Exception {

        String resumeText =
                resumeService.extractText(id);

        var skills =
                skillService.extractSkillsFromResume(
                        resumeText);

        var atsResult =
                atsService.calculateScore(
                        resumeText,
                        skills);

        return recommendationService.generateRecommendations(
                atsResult.getAtsScore(),
                atsResult.getMatchedSkills(),
                atsResult.getMissingSkills()
        );
    }
    @GetMapping("/{id}/interview-questions")
    public InterviewQuestionResponse getInterviewQuestions(
            @PathVariable Long id)
            throws Exception {

        String resumeText =
                resumeService.extractText(id);

        var skills =
                skillService.extractSkillsFromResume(
                        resumeText);

        var questions =
                interviewQuestionService.generateQuestions(
                        skills);

        long skillsCovered = questions.stream()
                .map(InterviewQuestion::getSkill)
                .distinct()
                .count();

        return new InterviewQuestionResponse(
                questions.size(),
                (int) skillsCovered,
                questions);
    }
    @PostMapping("/interview/evaluate")
    public AnswerEvaluationResponse evaluateAnswer(
            @RequestBody AnswerEvaluationRequest request) {

        return answerEvaluationService.evaluateAnswer(
                request.getSkill(),
                request.getDifficulty(),
                request.getQuestion(),
                request.getAnswer()
        );
    }
}