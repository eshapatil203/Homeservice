package com.homeservice1.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.homeservice1.entity.Service;
import com.homeservice1.util.HibernateUtil;

public class ServiceDao {

    public void save(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(service);
            tx.commit();
        }
    }

    public Service getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Service.class, id);
        }
    }

    public List<Service> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Service", Service.class).list();
        }
    }

    public void update(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(service);
            tx.commit();
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Service s = session.get(Service.class, id);
            if (s != null) session.delete(s);
            tx.commit();
        }
    }
}
