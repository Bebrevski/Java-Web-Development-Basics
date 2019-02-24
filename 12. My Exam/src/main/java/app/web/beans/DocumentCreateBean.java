package app.web.beans;

import app.domain.model.binding.DocumentCreateBindibgModel;
import app.domain.model.service.DocumentServiceModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("docCreateBean")
@RequestScoped
public class DocumentCreateBean extends BaseBean{
    private DocumentCreateBindibgModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentCreateBean() {
    }

    @Inject
    public DocumentCreateBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.model = new DocumentCreateBindibgModel();
    }

    public DocumentCreateBindibgModel getModel() {
        return this.model;
    }

    public void setModel(DocumentCreateBindibgModel model) {
        this.model = model;
    }

    public void insertDocument(){
        if (this.model.getContent().equals("") || this.model.getTitle().equals("")){
            this.redirect("schedule");
            return;
        }

        if (!this.documentService.insertDocument(this.modelMapper.map(this.model, DocumentServiceModel.class))){
            throw new IllegalArgumentException("SOMETHING WENT WRONG!!!!!!!!!!!!!!!!!!");
        }

        this.redirect("home");
    }
}
