package info.idb.crud.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import info.idb.crud.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final EntityManager em;

    public void save(User user) {
        if (user.hasId()) {
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    public void deleteById(Long id) {
        User user = new User();
        user.setId(id);
        delete(user);
    }

    public void delete(User user) {
        em.remove(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :uname", User.class);
        query.setParameter("uname", username);
        return query.getSingleResult();
    }

    public boolean existsByUsername(String username) {
        TypedQuery<Integer> query = em.createQuery("select count(u.id) from User u where u.username = :uname",
                Integer.class);
        query.setParameter("uname", username);
        Integer result = query.getSingleResult();
        return result != null && result > 0;
    }

    public boolean existsByEmail(String email) {
        TypedQuery<Integer> query = em.createQuery("select count(u.id) from User u where u.email = :email",
                Integer.class);
        query.setParameter("email", email);
        return query.getSingleResult() != null && query.getSingleResult() > 0;
    }

    public String loginValid(String username, String password) {
        Query query = em.createNativeQuery("select * from users where username = :username and password = :password",
                User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        @SuppressWarnings("unchecked")
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return "Username or password not valid";
        }
        return "Login success";
    }
}
