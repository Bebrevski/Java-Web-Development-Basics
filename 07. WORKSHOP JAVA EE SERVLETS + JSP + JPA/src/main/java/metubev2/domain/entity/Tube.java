package metubev2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tubes")
public class Tube extends BaseEntity {
    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private long views;
    private User uploader;

    public Tube() {
    }

    @Column(name = "title", nullable = false, updatable = true)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false, updatable = true)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", updatable = true)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youtube_id", nullable = false, updatable = false, unique = true)
    public String getYoutubeId() {
        return this.youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @Column(name = "views", nullable = false, updatable = true)
    public long getViews() {
        return this.views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uploader", referencedColumnName = "id", nullable = false)
    public User getUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
