package com.homeservice1.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.homeservice1.entity.Feedback;
import com.homeservice1.util.HibernateUtil;

public class FeedbackDAO {

    public void save(Feedback feedback) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(feedback);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Feedback getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Feedback.class, id);
        }
    }

    public List<Feedback> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Feedback", Feedback.class).list();
        }
    }

    public void update(Feedback feedback) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(feedback);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Feedback feedback = session.get(Feedback.class, id);
            if (feedback != null) {
                tx = session.beginTransaction();
                session.delete(feedback);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
