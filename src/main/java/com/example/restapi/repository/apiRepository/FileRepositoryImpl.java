package com.example.restapi.repository.apiRepository;

import com.example.restapi.model.File;
import com.example.restapi.repository.FileRepository;
import org.hibernate.Session;

import java.util.List;

import static com.example.restapi.util.HibernateSessionFactoryUtil.session;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public File save(File target) {
        try (Session session = session()) {
            session.beginTransaction();
            target = File.builder()
                    .name(target.getName())
                    .filePath(target.getFilePath())
                    .build();
            File fileCreate = session.merge(target);
            session.getTransaction().commit();
            return fileCreate;
        }
    }
    @Override
    public File update(File target, Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            target = File.builder()
                    .id(id)
                    .name(target.getName())
                    .filePath(target.getFilePath())
                    .build();
            session.saveOrUpdate(target);
            session.getTransaction().commit();
            return target;
        }
    }
    @Override
    public File getId(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            File getByIdFile = session.get(File.class, id);
            session.getTransaction().commit();
            return getByIdFile;
        }
    }
    @Override
    public List<File> getAll() {
        try (Session session = session()) {
            session.beginTransaction();
            List<File> file = session.createQuery("from File", File.class).getResultList();
            session.getTransaction().commit();
            return file;
        }
    }
    @Override
    public boolean deleteById(Integer id) {
        try (Session session = session()) {
            session.beginTransaction();
            File deleteFileById = session.get(File.class,id);
            session.remove(deleteFileById);
            session.getTransaction().commit();
            return true;
        }
    }
}
