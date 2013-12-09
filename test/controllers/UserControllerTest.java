package controllers;

import org.junit.*;
import play.libs.Yaml;
import java.util.*;
import com.avaje.ebean.*;

import static org.fest.assertions.api.Assertions.*;
import static play.test.Helpers.*;

import play.mvc.*;

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
       fail("not yet");
    }

    @Test public void callShow() {
       fail("not yet");
    }

    @Test public void callDelete() {
       fail("not yet");
    }
}
