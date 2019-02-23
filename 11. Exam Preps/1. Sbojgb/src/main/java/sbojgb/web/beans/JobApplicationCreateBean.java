package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.binding.JobApplicationCreateBindingModel;
import sbojgb.domain.models.service.JobApplicationServiceModel;
import sbojgb.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationCreateBean")
@RequestScoped
public class JobApplicationCreateBean extends BaseBean{
    private JobApplicationCreateBindingModel model;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new JobApplicationCreateBindingModel();
    }

    public void register(){
        if (!this.jobApplicationService.registerJobApplication(this.modelMapper.map(this.model, JobApplicationServiceModel.class))){
            throw new IllegalArgumentException("SOMETHING WENT WRONG!!!!!!!!!!!!!!!!!!");
        }

        this.redirect("home");
    }

    public JobApplicationCreateBindingModel getModel() {
        return this.model;
    }

    public void setModel(JobApplicationCreateBindingModel model) {
        this.model = model;
    }
}
