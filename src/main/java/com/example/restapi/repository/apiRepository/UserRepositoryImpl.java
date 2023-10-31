package com.example.restapi.repository.apiRepository;

import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.util.List;

import static com.example.restapi.util.HibernateSessionFactoryUtil.session;

public class UserRepositoryImpl implements UserRepository {
    @Override
    @Transactional
    public User save(User user) {
        try (Session session = session()) {
            session.beginTransaction();
            user = User.builder()
                    .name(user.getName())
                    .build();
            User createUser = session.merge(user);
            session.getTransaction().commit();
            return createUser;
        }
    }

    @Override
    @Transactional
    public User update(User user, Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            user = User.builder()
                    .name(user.getName())
                    .build();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User getId(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            User getByIdUser = session.get(User.class, id);
            session.getTransaction().commit();
            return getByIdUser;
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = session()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from Users", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            User deleteUserById = session.get(User.class, id);
            session.remove(deleteUserById);
            session.getTransaction().commit();
            return true;

        }
    }
}