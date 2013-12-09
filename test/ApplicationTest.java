import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

public class ApplicationTest {
    @Test public void requestRootGet() {
       start(fakeApplication());

       Result result = route(fakeRequest(GET, "/"));
       assertThat(header("Location", result)).isEqualTo("/users");
    }
}
