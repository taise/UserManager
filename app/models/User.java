package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import play.data.validation.Constraints.*;
import play.db.ebean.*;
import static play.db.ebean.Model.Finder;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @Required
    public String name;

    @Column(unique=true)
    public String email;
    public String password;

    public Date createdAt;
    public Date updatedAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public static Finder<Long, User> find
        = new Finder<Long, User>(Long.class, User.class);

    public static User findByEmail(String email) {
        return User.find.where().eq("email", email).findUnique();
    }
}
