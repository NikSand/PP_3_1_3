package springsecurity.springbootsecurity.services;

import org.springframework.stereotype.Service;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.dao.RoleDao;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao roleRepository;

    public RoleServiceImp(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Set<Role> findAllRoleId(List<Long> ids) {
        return roleRepository.findAllId(ids);
    }
}
