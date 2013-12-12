package helper;

import java.util.*;
import play.libs.Yaml;
import com.avaje.ebean.*;

import models.User;

public class UserHelper {
  public static User getFirstUser() {
      Ebean.save((List) Yaml.load("testData/users.yml"));
      List<User> users = User.find.findList();
      return users.get(0);
  }
}
