package controllers;

import org.junit.*;
import static org.fest.assertions.api.Assertions.*;
import static play.test.Helpers.*;

import play.mvc.*;

public class UserControllerTest {
    @Test public void callIndex() {
        Result result = callAction(
            controllers.routes.ref.UserController.index()
                );
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test public void callNewForm() {
       fail("not yet");
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
