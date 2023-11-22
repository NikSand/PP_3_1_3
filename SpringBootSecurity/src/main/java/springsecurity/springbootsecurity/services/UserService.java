package springsecurity.springbootsecurity.services;

import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserService  {

    List<springsecurity.springbootsecurity.model.User> getAllUsers();

    springsecurity.springbootsecurity.model.User getUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User findByUsername(String username);

}