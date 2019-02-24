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
    }

    @PostConstruct
    private void init() {
        this.model = new DocumentViewModel();

        var sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        String docId = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getParameter("docId");

        if (docId != null) {

            this.model = this.modelMapper.map(this.documentService.findById(docId), DocumentViewModel.class);

            sessionMap.put("docId", this.model.getId());

        } else {
            String savedId = (String) FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().get("docId");

            this.model = this.modelMapper.map(this.documentService.findById(savedId), DocumentViewModel.class);

            sessionMap.remove("docId");
        }
    }

    public DocumentViewModel getDoc(String id) {
        return this.modelMapper.map(this.documentService.findById(id), DocumentViewModel.class);

    }

    public void print() {
        this.documentService.delete(this.model.getId());
        this.redirect("home");
    }
}
