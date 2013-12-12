package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
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
        Form<User> userForm = form(User.class).bindFromRequest();
        User user = userForm.get();
        user.save();
        return redirect(routes.UserController.index());
    }

    public static Result show(Long id) {
      User user = User.find.byId(id);
      return ok(show.render(user));
    }

    public static Result delete(Long id) {
        User.find.byId(id).delete();
        return redirect(routes.UserController.index());
    }
}
