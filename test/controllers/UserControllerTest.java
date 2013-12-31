package controllers;

import org.junit.*;
import play.libs.Yaml;
import java.util.*;
import com.avaje.ebean.*;

import static org.fest.assertions.api.Assertions.*;
import static play.test.Helpers.*;

import play.mvc.*;
import models.User;
import helper.UserHelper;

public class UserControllerTest {
    @Before public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test public void callIndex() {
        Result result = route(fakeRequest(GET, "/users"));

        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
    }

    @Test public void callNewForm() {
        Result result = route(fakeRequest(GET, "/users/new"));

        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
    }

    @Test public void callCreateOK() {
        Map<String, String> params = new HashMap<String,String>();
        params.put("name", "Alice");
        params.put("email", "alice@email.com");
        params.put("password", "password");
        int beforeCount = User.find.findRowCount();

        Result result = route(
                fakeRequest(POST, "/users")
                .withFormUrlEncodedBody(params)
                );

        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/users");
        assertThat(User.find.findRowCount()).isEqualTo(beforeCount + 1);
    }

    @Test public void callCreateBadRequest() {
        Map<String, String> params = new HashMap<String,String>();
        params.put("email", "alice@email.com");
        params.put("password", "password");
        int beforeCount = User.find.findRowCount();

        Result result = route(
                fakeRequest(POST, "/users")
                .withFormUrlEncodedBody(params)
                );

        assertThat(status(result)).isEqualTo(BAD_REQUEST);
        assertThat(User.find.findRowCount()).isEqualTo(beforeCount);
    }

    @Test public void callShow() {
        User user = UserHelper.getFirstUser();

        Result result = route(fakeRequest(GET, "/users/" + user.id));

        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
    }

    @Test public void callDelete() {
        User user = UserHelper.getFirstUser();

        Result result = route(fakeRequest(POST, "/users/delete/" + user.id));

        assertThat(User.find.byId(user.id)).isNull();
        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/users");
    }
}
