package app.service;


import app.domain.model.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    boolean insertDocument(DocumentServiceModel documentServiceModel);

    List<DocumentServiceModel> findAllDocuments();

    DocumentServiceModel findById(String id);

    void delete(String id);
}
