package app.service;

import app.domain.entity.Document;
import app.domain.model.service.DocumentServiceModel;
import app.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean insertDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);

        if (this.documentRepository.save(document) == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<DocumentServiceModel> findAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(u -> {
                    DocumentServiceModel model = this.modelMapper.map(u, DocumentServiceModel.class);

                    return model;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel findById(String id) {
        return this.modelMapper.map(this.documentRepository.findById(id), DocumentServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.documentRepository.printDocumentById(id);
    }
}
