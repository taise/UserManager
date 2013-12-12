package views;

import org.junit.Test;
import org.junit.Before;

import play.mvc.Content;
import java.util.List;
import play.libs.Yaml;
import com.avaje.ebean.*;

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

      Content html = index.render();
      String htmlString = contentAsString(html);

      assertThat(htmlString).contains("<td>" + user.name + "</td>");
      assertThat(htmlString).contains("<td>" + user.email+ "</td>");
      assertThat(htmlString).contains("<a href=\"/users/" + user.id + "\">link</a></td>");
    }

    @Test public void newFormRender() {
        Content html = newForm.render(form(User.class));
        String htmlString = contentAsString(html);

        assertThat(htmlString).contains("<input type=\"text\" id=\"name\"");
        assertThat(htmlString).contains("<input type=\"text\" id=\"email\"");
        assertThat(htmlString).contains("<input type=\"password\" id=\"password\"");
        assertThat(htmlString).contains("<input type=\"password\" id=\"password-conf\"");
    }

    @Test public void showRender() {
      User user = UserHelper.getFirstUser();

      Content html = show.render(user);
      String htmlString = contentAsString(html);

      assertThat(htmlString).contains("<td>" + user.name + "</td>");
      assertThat(htmlString).contains("<td>" + user.email+ "</td>");
    }
}
