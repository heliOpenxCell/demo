package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

public interface IUserDAO {

    public List<User> getUsers();

    public User getUser(String username);

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(String username);

}
