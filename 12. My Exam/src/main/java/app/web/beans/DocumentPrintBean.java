package app.web.beans;

import app.domain.model.view.DocumentViewModel;
import app.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Named("docPrintBean")
public class DocumentPrintBean extends BaseBean {
    private DocumentViewModel model;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.init();
    }

    @PostConstruct
    private void init() {
        this.model = new DocumentViewModel();
    }

    public DocumentViewModel getDoc(String id) {
        return this.modelMapper.map(this.documentService.findById(id), DocumentViewModel.class);

    }

    public void print() {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("docId");
        this.documentService.delete(id);
        this.redirect("home");
    }
}
