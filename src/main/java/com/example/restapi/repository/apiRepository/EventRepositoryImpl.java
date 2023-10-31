package com.example.restapi.repository.apiRepository;

import com.example.restapi.model.Event;
import com.example.restapi.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.util.List;

import static com.example.restapi.util.HibernateSessionFactoryUtil.session;

public class EventRepositoryImpl implements EventRepository {
    private static String UPDATE = "Event SET user.id = :userId , file.id  = :fileId where id = :eventId";
    @Override
    @Transactional
    public Event save(Event target) {
        try (Session session = session()) {
            session.beginTransaction();
            session.merge(target);
            session.getTransaction().commit();
            return target;
        }
    }
    @Override
    @Transactional
    public Event update(Event target, Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            session.createQuery(UPDATE)
                    .setParameter("eventId", target.getId())
                    .setParameter("userId", target.getUser().getId())
                    .setParameter("fileId", target.getFile().getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return target;
        }
    }
    @Override
    public Event getId(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            Event getByIdEvent = session.get(Event.class, id);
            session.getTransaction().commit();
            return getByIdEvent;
        }
    }
    @Override
    public List<Event> getAll() {
        try (Session session = session()) {
            session.beginTransaction();
            List<Event> events = session.createQuery("from Event", Event.class).getResultList();
            session.getTransaction().commit();
            return events;
        }
    }
    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            Event deleteEventById = session.get(Event.class, id);
            session.remove(deleteEventById);
            session.getTransaction().commit();
            return true;
        }
    }


}
