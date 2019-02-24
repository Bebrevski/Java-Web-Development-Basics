package app.web.beans;

import app.domain.model.view.DocumentViewModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("docListBean")
@RequestScoped
public class DocumentListBean {
    private List<DocumentViewModel> models;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentListBean() {
    }

    @Inject
    public DocumentListBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.models = this.documentService.findAllDocuments().stream()
                .map(d -> {
                    DocumentViewModel model = this.modelMapper.map(d, DocumentViewModel.class);
                    if (model.getTitle().length() >= 12) {
                        model.setTitle(model.getTitle().substring(0, 12) + "...");
                    }

                    return model;
                })
                .collect(Collectors.toList());
    }

    public List<DocumentViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<DocumentViewModel> models) {
        this.models = models;
    }
}
