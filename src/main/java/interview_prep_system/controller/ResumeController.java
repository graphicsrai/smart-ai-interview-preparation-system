package interview_prep_system.controller;

import interview_prep_system.dto.ResumeUploadResponse;
import interview_prep_system.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import interview_prep_system.dto.ResumeTextResponse;
import interview_prep_system.dto.SkillResponse;
import interview_prep_system.service.SkillService;
@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final ResumeService resumeService;
    private final SkillService skillService;
    public ResumeController(
            ResumeService resumeService,
            SkillService skillService) {

        this.resumeService = resumeService;
        this.skillService = skillService;
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
}