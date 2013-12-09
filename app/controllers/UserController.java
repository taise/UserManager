package controllers;

import play.*;
import play.mvc.*;
import static play.data.Form.form;

import java.util.List;
import models.User;
import views.html.user.*;

public class UserController extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result newForm() {
        return ok(newForm.render(form(User.class)));
    }

    public static Result create() {
        return ok();
    }
}
