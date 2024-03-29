package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void addUser(User user);
    void deleteUser(int id);
    void editUser(int id, User user);
    User getUserId(int id);
}
