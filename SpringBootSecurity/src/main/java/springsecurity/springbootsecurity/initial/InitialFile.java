package springsecurity.springbootsecurity.initial;

import org.springframework.stereotype.Component;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.RoleService;
import springsecurity.springbootsecurity.services.UserService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialFile {

    private final UserService userService;

    private final RoleService roleService;

    public InitialFile(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
    }

    @Transactional
    @PostConstruct
    public void run() {

        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));

        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        adminRole.add(roleService.getRoleById(1L));
        userRole.add(roleService.getRoleById(2L));

        userService.addUser(new User("Ivan", "Domrachev", (byte) 42, "Russian", "admin", "12345", adminRole));
        userService.addUser(new User("Pavel", "Sidorov", (byte) 19, "Russian", "user", "54321", userRole));
        }
}
