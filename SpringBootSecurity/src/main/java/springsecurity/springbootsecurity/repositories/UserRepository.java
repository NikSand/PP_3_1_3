package springsecurity.springbootsecurity.repositories;

import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserRepository {

     List<User> findAll();

     User getById(Long id);

     void save(User user);

     void updateUser(User user);

     void deleteById(Long id);

     User findByUsername(String username);
}