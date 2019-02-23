package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.service.JobApplicationServiceModel;
import sbojgb.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobDetailsBean")
@RequestScoped
public class JobDetailsBean {
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobApplicationServiceModel getJob(String id){
        return this.jobApplicationService.getJobById(id);
    }
}
