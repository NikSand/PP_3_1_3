package springsecurity.springbootsecurity.dao;

import org.springframework.stereotype.Repository;
import springsecurity.springbootsecurity.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List <User> findAll() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) throws IllegalArgumentException {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select user from User user where user.username = : username", User.class)
                .setParameter("username", username)
                .setMaxResults(1).getSingleResult();
    }
}
