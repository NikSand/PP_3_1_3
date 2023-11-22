package springsecurity.springbootsecurity.dao;

import springsecurity.springbootsecurity.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> findAll();

    Set<Role> findAllId(List<Long> ids);

    Role getById(Long id);

    void save(Role role);

}
