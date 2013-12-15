package controllers;

import play.*;
import play.mvc.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import models.User;

public class ApiController extends Controller {

    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result allUsers() {
        List<User> users = User.find.findList();
        JsonNode json = Json.toJson(users);
        return ok(json);
    }

    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public static Result showUser(Long id) {
        User user = User.find.byId(id);
        JsonNode json = Json.toJson(user);
        return ok(json);
    }
}
