package com.homeservice1.service;

import com.homeservice1.entity.*;
import com.homeservice1.dao.*;
import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public void registerUser(User user) {
        // business rule: unique email
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            System.out.println("⚠️ Email already registered: " + user.getEmail());
            return;
        }
        userDAO.saveUser(user);
        System.out.println("✅ Registration successful for: " + user.getName());
    }

    public User login(String email, String password) {
        User u = userDAO.getUserByEmail(email);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }

    public List<User> listAllUsers() { return userDAO.getAllUsers(); }
    public User getUserById(int id) { return userDAO.getUserById(id); }
    public void updateUser(User user) { userDAO.updateUser(user); }
    public void deleteUser(int id) { userDAO.deleteUser(id); }
}
