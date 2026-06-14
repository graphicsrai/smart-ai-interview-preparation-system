package interview_prep_system.dto;

public class JobInterviewQuestionRequest {

    private Long resumeId;

    private String jobDescription;

    public JobInterviewQuestionRequest() {
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}