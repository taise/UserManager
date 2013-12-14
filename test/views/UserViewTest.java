package views;

import org.junit.Test;
import org.junit.Before;

import models.User;
import views.html.user.*;
import helper.UserHelper;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;
import static play.data.Form.form;

public class UserViewTest {
    @Before public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test public void indexRender() {
      User user = UserHelper.getFirstUser();

      String htmlString
        = contentAsString(index.render());

      assertThat(htmlString).contains("<td>" + user.name + "</td>");
      assertThat(htmlString).contains("<td>" + user.email+ "</td>");
      assertThat(htmlString).contains("<a href=\"/users/" + user.id + "\">link</a></td>");
    }

    @Test public void newFormRender() {
      String htmlString
        = contentAsString(newForm.render(form(User.class)));

        assertThat(htmlString).contains("<input type=\"text\" id=\"name\"");
        assertThat(htmlString).contains("<input type=\"text\" id=\"email\"");
        assertThat(htmlString).contains("<input type=\"password\" id=\"password\"");
        assertThat(htmlString).contains("<input type=\"password\" id=\"password-conf\"");
    }

    @Test public void showRender() {
      User user = UserHelper.getFirstUser();

      String htmlString
        = contentAsString(show.render(user));

      assertThat(htmlString).contains("<td>" + user.name + "</td>");
      assertThat(htmlString).contains("<td>" + user.email+ "</td>");
    }
}
