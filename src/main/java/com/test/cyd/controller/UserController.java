package com.test.cyd.controller;

import com.test.cyd.entity.User;
import com.test.cyd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  // Home
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String showUserForm(Model model) {
    model.addAttribute("user", new User());
    return "home";
  }

  // Users
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String usersList(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "users_index";
  }

  @RequestMapping(value = "/users/new", method = RequestMethod.GET)
  public String usersNew(Model model) {
    model.addAttribute("user", new User());
    return "users_create";
  }

  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public String usersCreate(Model model, User user) {
    User existing = userService.findByUserId(user.getUserId());
    if (existing != null) {
      model.addAttribute("error", "User already exist !");
      model.addAttribute("users", userService.getAllUsers());
      return "users_index";
    }
    user.setCreatedDate(new Date());
    userService.saveUser(user);
    model.addAttribute("info", "User created successfully");
    return "users_get";
  }

  @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
  public String usersRetrieve(@PathVariable String userId, Model model) {
    User user = userService.findByUserId(userId);
    model.addAttribute("user", user);
    return "users_get";
  }

  @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.GET)
  public String usersEdit(@PathVariable String userId, Model model) {
    User user = userService.findByUserId(userId);
    model.addAttribute("user", user);
    return "users_update";
  }

  @RequestMapping(value = "/users/update", method = RequestMethod.POST)
  public String usersUpdate(Model model, User user) {
    User oldUser = userService.findByUserId(user.getUserId());
    oldUser.setFirstname(user.getFirstname());
    oldUser.setLastname(user.getLastname());
    userService.saveUser(oldUser);
    model.addAttribute("info", "User updated successfully");
    model.addAttribute("user", oldUser);
    return "users_get";
  }

  @RequestMapping(value = "/users/delete/{userId}", method = {RequestMethod.GET})
  public String usersDelete(@PathVariable String userId, Model model) {
    userService.deleteUser(userId);
    model.addAttribute("info", "User deleted successfully");
    model.addAttribute("users", userService.getAllUsers());
    return "users_index";
  }

  @RequestMapping(value = "/users/search", method = RequestMethod.POST)
  public String usersSearch(Model model, User user) {
    List<User> users = userService.findUsers(user.getUserId());
    model.addAttribute("users", users);
    model.addAttribute("search", true);
    return "users_index";
  }

}
