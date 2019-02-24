package app.web.beans;

import app.domain.model.service.DocumentServiceModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("docDetailBean")
@RequestScoped
public class DocumentDetailBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailBean() {
    }

    @Inject
    public DocumentDetailBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentServiceModel getDocumentById(String id){
        return this.documentService.findById(id);
    }
}
