package app.domain.model.binding;

public class DocumentCreateBindibgModel {
    private String title;
    private String content;


    public DocumentCreateBindibgModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
