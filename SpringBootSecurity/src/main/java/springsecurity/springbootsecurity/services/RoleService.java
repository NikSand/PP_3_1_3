package springsecurity.springbootsecurity.services;

import springsecurity.springbootsecurity.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);

    Role getRoleById(Long id);

    Set<Role> findAllRoleId(List<Long> ids);
}
