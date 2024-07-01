package com.thedisciplineprogram.repositories.user;

import com.thedisciplineprogram.models.db_entities.User;
import com.thedisciplineprogram.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDAOImpl implements UserDAO {

    @Override
    public User getUserById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User result = session.get(User.class, id);
            log.info("User is got from DB");
            return result;
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return new User();
    }

    @Override
    public User getUserByEmail(String email) {
        User result = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<User> criteria = builder.createQuery(User.class);
                Root<User> from = criteria.from(User.class);
                criteria.select(from);
                criteria.where(builder.equal(from.get("email"), email));
                TypedQuery<User> typed = session.createQuery(criteria);
                result = typed.getSingleResult();
                log.info("User is got from DB by email '{}'", email);
            }  catch (NoResultException e) {
                log.error("User with email '{}' not found in DB", email);
                log.error(e.getMessage());
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Boolean createUser(User user) {
        boolean isCreated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = getUserByEmail(user.getEmail());
            if (existingUser == null) {
                session.getTransaction().begin();
                session.persist(user);
                session.getTransaction().commit();
                log.info("User created in DB");
                isCreated = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public Boolean updateUser(User user) {
        boolean isUpdated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = session.get(User.class, user.getId());
            if (existingUser != null) {
                session.getTransaction().begin();
                session.merge(user);
                session.getTransaction().commit();
                log.info("User updated in DB");
                isUpdated = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public Boolean deleteUserById(Long id) {
        boolean isDeleted = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User existingUser = session.get(User.class, id);
            if (existingUser != null) {
                session.getTransaction().begin();
                session.remove(existingUser);
                session.getTransaction().commit();
                log.info("User deleted in DB");
                isDeleted = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isDeleted;
    }
}
