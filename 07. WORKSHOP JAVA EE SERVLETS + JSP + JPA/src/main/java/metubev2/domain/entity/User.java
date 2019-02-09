package metubev2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private List<Tube> tubes;

    public User() {
    }

    @Column(name = "username", nullable = false, unique = true, updatable = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, updatable = true)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, updatable = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "tubes")
    @OneToMany(targetEntity = Tube.class, mappedBy = "uploader")
    public List<Tube> getTubes() {
        return Collections.unmodifiableList(this.tubes);
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
