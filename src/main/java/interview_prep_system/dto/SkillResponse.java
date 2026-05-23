package interview_prep_system.dto;

import java.util.List;

public class SkillResponse {

    private List<String> skills;

    public SkillResponse(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }
}