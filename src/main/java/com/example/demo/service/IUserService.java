package com.example.demo.service;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface IUserService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserByUsername(String username);

    public void createUser(UserDTO user);

    public void updateUser(UserDTO user);

    public void deleteUser(String username);

}
