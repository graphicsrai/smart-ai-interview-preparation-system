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

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final ResumeService resumeService;
    private final SkillService skillService;

    private final AtsService atsService;
    public ResumeController(
            ResumeService resumeService,
            SkillService skillService,
            AtsService atsService) {

        this.resumeService = resumeService;
        this.skillService = skillService;
        this.atsService = atsService;
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
}