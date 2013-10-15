package com.test.cyd.service;

import com.test.cyd.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl {

  @Autowired
  private SessionFactory sessionFactory;

  public User findByUserId(String userId) {
    return (User) sessionFactory.getCurrentSession().get(User.class, userId);
  }

  @Transactional(readOnly = false)
  public void saveUser(User user) {
    sessionFactory.getCurrentSession().saveOrUpdate(user);
  }

  @Transactional(readOnly = false)
  public void deleteUser(String userId) {
    User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
    if (user != null) {
      sessionFactory.getCurrentSession().delete(user);
    }
  }

  public List<User> findUsers(String userId) {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
    criteria.add(Restrictions.like(User.USERID, userId, MatchMode.START));
    return criteria.list();
  }

  public List<User> getAllUsers() {
    return sessionFactory.getCurrentSession().createCriteria(User.class).list();
  }
}
