package app.repository;

import app.domain.entity.Document;

public interface DocumentRepository extends GenericRepository<Document, String>{
    void printDocumentById(String id);
}
