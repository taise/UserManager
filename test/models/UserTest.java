package models;

import models.User;
import org.junit.*;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

public class UserTest {
    @Test
    public void userFindByEmail() {
        String email = "alice@email.com";
        start(fakeApplication(inMemoryDatabase()));
        User user = new User("alice", email, "password");
        user.save();

        User actual = User.findByEmail(email);

        assertThat(actual.email).isEqualTo(email);
    }
}
