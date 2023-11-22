package springsecurity.springbootsecurity.dao;

import org.springframework.stereotype.Repository;
import springsecurity.springbootsecurity.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Set<Role> findAllId(List<Long> ids) {
        String jpql = "SELECT r FROM Role r WHERE r.id IN :ids";
        return new HashSet<>(entityManager.createQuery(jpql, Role.class)
                .setParameter("ids", ids)
                .getResultList());
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
