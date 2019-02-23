package sbojgb.service;

import sbojgb.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    boolean registerJobApplication(JobApplicationServiceModel jobApplicationServiceModel);

    List<JobApplicationServiceModel> getAllJobs();
}
