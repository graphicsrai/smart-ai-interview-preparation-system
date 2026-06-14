package interview_prep_system.controller;

import interview_prep_system.dto.*;
import interview_prep_system.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import interview_prep_system.service.SkillService;
import interview_prep_system.service.AtsService;
import interview_prep_system.service.ResumeRecommendationService;
import interview_prep_system.service.InterviewQuestionService;
import interview_prep_system.service.JobInterviewQuestionService;
import interview_prep_system.dto.JobInterviewQuestionRequest;
import interview_prep_system.dto.JobInterviewQuestionResponse;
import interview_prep_system.service.AnswerEvaluationService;

import interview_prep_system.service.DashboardService;
import interview_prep_system.service.JobMatchService;
import interview_prep_system.dto.JobDescriptionRequest;
import interview_prep_system.dto.JobMatchResponse;
import java.util.List;

import interview_prep_system.service.GeminiService;
import interview_prep_system.dto.GeminiResumeAnalysisResponse;

import interview_prep_system.service.AiAnswerEvaluationService;
import interview_prep_system.dto.AiAnswerEvaluationResponse;

import java.util.List;
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
    private final JobMatchService jobMatchService;
    private final DashboardService dashboardService;
    private final JobInterviewQuestionService
            jobInterviewQuestionService;
    private final GeminiService geminiService;

    private final AiAnswerEvaluationService
            aiAnswerEvaluationService;
    public ResumeController(
            ResumeService resumeService,
            SkillService skillService,
            AtsService atsService,
            ResumeRecommendationService recommendationService,
            InterviewQuestionService interviewQuestionService,
            AnswerEvaluationService answerEvaluationService,
            DashboardService dashboardService,
            JobMatchService jobMatchService,
            JobInterviewQuestionService jobInterviewQuestionService,
            GeminiService geminiService,
            AiAnswerEvaluationService aiAnswerEvaluationService) {

        this.resumeService = resumeService;
        this.skillService = skillService;
        this.atsService = atsService;
        this.recommendationService = recommendationService;
        this.interviewQuestionService = interviewQuestionService;
        this.answerEvaluationService = answerEvaluationService;
        this.dashboardService = dashboardService;
        this.jobMatchService = jobMatchService;
        this.jobInterviewQuestionService = jobInterviewQuestionService;
        this.geminiService = geminiService;
        this.aiAnswerEvaluationService = aiAnswerEvaluationService;
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

    @GetMapping("/dashboard/summary")
    public DashboardSummaryResponse getDashboardSummary() {

        return dashboardService.getSummary();
    }
    @GetMapping("/dashboard/analytics")
    public DashboardAnalyticsResponse getDashboardAnalytics() {

        return dashboardService.getAnalytics();
    }

    @GetMapping("/dashboard/skills")
    public List<SkillPerformanceResponse> getSkillPerformance() {

        return dashboardService.getSkillPerformance();
    }

    @GetMapping("/dashboard/history")
    public List<InterviewHistoryResponse> getRecentHistory() {

        return dashboardService.getRecentHistory();
    }

    @GetMapping("/all")
    public List<ResumeListResponse> getAllResumes() {

        return resumeService.getAllResumes();
    }

    @PostMapping("/job-match")
    public JobMatchResponse matchResumeWithJobDescription(
            @RequestBody
            JobDescriptionRequest request)
            throws Exception {

        return jobMatchService
                .matchResumeWithJobDescription(
                        request.getResumeId(),
                        request.getJobDescription());
    }
    @PostMapping("/job-interview-questions")
    public JobInterviewQuestionResponse
    generateJobInterviewQuestions(
            @RequestBody
            JobInterviewQuestionRequest request)
            throws Exception {

        var questions =
                jobInterviewQuestionService
                        .generateQuestions(
                                request.getResumeId(),
                                request.getJobDescription());

        long skillsCovered =
                questions.stream()
                        .map(InterviewQuestion::getSkill)
                        .distinct()
                        .count();

        return new JobInterviewQuestionResponse(
                questions.size(),
                (int) skillsCovered,
                questions
        );
    }
    @GetMapping("/{id}/ai-analysis")
    public GeminiResumeAnalysisResponse
    getAiResumeAnalysis(
            @PathVariable Long id)
            throws Exception {

        String resumeText =
                resumeService.extractText(id);

        String analysis =
                geminiService.analyzeResume(
                        resumeText);

        return new GeminiResumeAnalysisResponse(
                analysis);
    }
    @PostMapping("/interview/ai-evaluate")
    public AiAnswerEvaluationResponse
    aiEvaluateAnswer(
            @RequestBody
            AnswerEvaluationRequest request)
            throws Exception {

        return aiAnswerEvaluationService.evaluate(
                request.getSkill(),
                request.getDifficulty(),
                request.getQuestion(),
                request.getAnswer()
        );
    }
}