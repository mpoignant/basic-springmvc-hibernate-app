package com.test.cyd.service;

import com.test.cyd.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserServiceTest {

  @Autowired
  private UserServiceImpl userService;

  private User createUser(String userId, String firstname, String lastname) {
    User user = new User(userId, firstname, lastname);
    user.setCreatedDate(new Date());
    userService.saveUser(user);
    return user;
  }

  @Test
  public void testSaveUser() throws Exception {
    User user = createUser("user_A", "first_A", "last_A");
    User found = userService.findByUserId(user.getUserId());
    Assert.assertEquals(user, found);
  }

  @Test
  public void testSearchUser() throws Exception {
    String userId = "user_B";
    createUser(userId, "first_B", "last_B");
    List<User> users = userService.findUsers(userId);
    Assert.assertEquals(1, users.size());
    Assert.assertEquals(userId, users.iterator().next().getUserId());
  }

  @Test
  public void testDeleteUser() throws Exception {
    String userId = "user_C";
    createUser(userId, "first_C", "last_C");
    Assert.assertNotNull(userService.findByUserId(userId));
    userService.deleteUser(userId);
    Assert.assertNull(userService.findByUserId(userId));
  }

  @Test
  public void testUpdateUser() throws Exception {
    String userId = "user_D";
    String firstname = "first_D";
    String lastname = "last_D";
    Assert.assertNull(userService.findByUserId(userId));
    createUser(userId, firstname, lastname);
    User user_1 = userService.findByUserId(userId);
    Assert.assertNotNull(user_1);
    Assert.assertEquals(firstname, user_1.getFirstname());
    Assert.assertEquals(lastname, user_1.getLastname());

    String otherfirstname = "other_first_D";
    String otherlastname = "other_last_D";
    user_1.setFirstname(otherfirstname);
    user_1.setLastname(otherlastname);
    userService.saveUser(user_1);

    User found = userService.findByUserId(userId);
    Assert.assertNotNull(found);
    Assert.assertEquals(otherfirstname, found.getFirstname());
    Assert.assertEquals(otherlastname, found.getLastname());
  }
}
