package views;

import org.junit.Test;
import play.mvc.Content;
import java.util.List;
import play.libs.Yaml;
import com.avaje.ebean.*;

import models.User;
import views.html.user.*;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

public class UserViewTest {
    @Test public void IndexRender() {
        start(fakeApplication(inMemoryDatabase()));
        Ebean.save((List) Yaml.load("testData/users.yml"));

        Content html = index.render();
        String htmlString = contentAsString(html);

        assertThat(htmlString).contains("<td>Alice</td>");
        assertThat(htmlString).contains("<td>alice@email.com</td>");
    }
}
