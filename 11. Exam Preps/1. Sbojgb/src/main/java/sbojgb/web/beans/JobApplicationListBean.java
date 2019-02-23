package sbojgb.web.beans;

import org.modelmapper.ModelMapper;
import sbojgb.domain.models.view.JobApplicationListViewModel;
import sbojgb.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("jobListBean")
@RequestScoped
public class JobApplicationListBean {
    private List<JobApplicationListViewModel> models;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.init();
    }

    @PostConstruct
    private void init(){
        this.models = this.jobApplicationService.getAllJobs().stream()
                .map(j -> this.modelMapper.map(j, JobApplicationListViewModel.class))
        .collect(Collectors.toList());
    }

    public List<JobApplicationListViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<JobApplicationListViewModel> models) {
        this.models = models;
    }
}
