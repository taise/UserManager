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
        Ebean.save((List) Yaml.load("testData/users.yml"));

        Result result = callAction(
            controllers.routes.ref.UserController.index()
                );

        assertThat(status(result)).isEqualTo(OK);
    }

    @Test public void callNewForm() {
        Result result = callAction(
                controllers.routes.ref.UserController.newForm()
                );

        assertThat(status(result)).isEqualTo(OK);
    }

    @Test public void callCreate() {
      Map<String, String> params = new HashMap<String,String>();
      params.put("name", "Alice");
      params.put("email", "alice@email.com");
      params.put("password", "password");
      int beforeCount = User.find.findRowCount();

      Result result = callAction(
          controllers.routes.ref.UserController.create(),
          fakeRequest().withFormUrlEncodedBody(params)
          );

      assertThat(status(result)).isEqualTo(SEE_OTHER);
      assertThat(header("Location", result)).isEqualTo("/users");
      assertThat(User.find.findRowCount()).isEqualTo(beforeCount + 1);
    }

    @Test public void callShow() {
      User user = UserHelper.getFirstUser();

      Result result = callAction(
          controllers.routes.ref.UserController.show(user.id)
          );

        assertThat(status(result)).isEqualTo(OK);
    }

    @Test public void callDelete() {
       fail("not yet");
    }
}
