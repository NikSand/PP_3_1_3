package springsecurity.springbootsecurity.services;



import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<springsecurity.springbootsecurity.model.User> getAllUsers();

    springsecurity.springbootsecurity.model.User getUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);
    springsecurity.springbootsecurity.model.User findByUsername(String username);

}