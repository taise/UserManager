package controllers;

import org.junit.*;
import play.libs.Yaml;
import java.util.*;
import com.avaje.ebean.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

import static org.fest.assertions.api.Assertions.*;
import static play.test.Helpers.*;

import play.mvc.*;
import models.User;
import helper.UserHelper;

public class ApiControllerTest {
    @Before public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Test public void callAllUser() {
        Ebean.save((List) Yaml.load("testData/users.yml"));
        Result result = callAction(
                controllers.routes.ref.ApiController.allUsers()
                );

        String content = contentAsString(result);
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(content).contains("[{\"id\":1")
                           .contains("},{\"id\":2");
    }

    @Test public void callShowUser() {
        User user = UserHelper.getFirstUser();
        Result result = callAction(
                controllers.routes.ref.ApiController.showUser(user.id)
                );

        String content = contentAsString(result);
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(content).contains("{\"id\":1");
    }
}
