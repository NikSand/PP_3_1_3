package springsecurity.springbootsecurity.services;

import org.springframework.stereotype.Service;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.repositories.RoleRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    @Transactional
    public Set<Role> findAllRoleId(List<Long> ids) {
        return roleRepository.findAllId(ids);
    }
}
