package springsecurity.springbootsecurity.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.dao.UserDao;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Security implements UserDetailsService {

    private final UserDao userRepository;

    public Security(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
