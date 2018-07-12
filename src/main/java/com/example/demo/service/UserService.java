package com.example.demo.service;

import com.example.demo.dao.IUserDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.ApiDTOBuilder;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//The class provides services only. Calls DAO class's methods and provides nevessary services like creating,deleting,updating and fetching data.
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> entities = userDAO.getUsers();
        List<UserDTO> users = new ArrayList<UserDTO>();//Will never use directly User Object, will be using Tranferable objects

        Iterator<User> iterator = entities.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();
            users.add(ApiDTOBuilder.userToUserDTO(user));//We are building UserDTO object.
        }
        return users;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userDAO.getUser(username);
        return ApiDTOBuilder.userToUserDTO(user);
    }

    @Override
    public void createUser(UserDTO user) {
        userDAO.createUser(ApiDTOBuilder.userDTOToUser(user));
    }

    @Override
    public void updateUser(UserDTO user) {
        userDAO.updateUser(ApiDTOBuilder.userDTOToUser(user));

    }

    @Override
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }
}
