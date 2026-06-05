package com.dormitory.service;

import com.dormitory.dao.UserDao;
import com.dormitory.model.User;

import java.util.List;

/**
 * 用户业务逻辑层
 */
public class UserService {
    
    private UserDao userDao = new UserDao();
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户对象，否则返回null
     */
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    public List<User> findAll() {
        return userDao.findAll();
    }
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    public User findById(Integer id) {
        return userDao.findById(id);
    }
    
    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean add(User user) {
        return userDao.add(user) > 0;
    }
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean update(User user) {
        return userDao.update(user) > 0;
    }
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean delete(Integer id) {
        return userDao.delete(id) > 0;
    }
}
