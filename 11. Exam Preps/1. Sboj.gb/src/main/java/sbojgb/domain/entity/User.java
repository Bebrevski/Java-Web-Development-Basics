package sbojgb.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String Email;

    public User() {
    }

    @Column(name = "username", nullable = false, updatable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "password", nullable = false, updatable = false)
    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
