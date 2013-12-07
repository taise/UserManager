package models;

import models.User;
import org.junit.*;
import java.util.List;
import play.libs.Yaml;
import com.avaje.ebean.Ebean;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class UserTest {
    static final String EMAIL = "alice@email.com";

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
        Ebean.save((List) Yaml.load("testData/users.yml"));
    }
    
    @Test
    public void findByEmail() {
        User actual = User.findByEmail(EMAIL);

        assertThat(actual.email).isEqualTo(EMAIL);
    }

    @Test
    public void dontFindByInvalidEmail() {
        String typoEmail = EMAIL + ".";
        User actual = User.findByEmail(typoEmail);

        assertThat(actual).isNull();
    }
}
