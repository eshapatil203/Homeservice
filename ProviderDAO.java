package com.homeservice1.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.homeservice1.entity.Provider;
import com.homeservice1.util.HibernateUtil;

public class ProviderDAO {

    // Save provider
    public void save(Provider provider) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(provider);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get provider by ID
    public Provider getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Provider.class, id);
        }
    }

    // Get all providers
    @SuppressWarnings("unchecked")
    public List<Provider> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Provider").list();
        }
    }

    // Update provider
    public void update(Provider provider) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(provider);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete provider by ID
    public void delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Provider provider = session.get(Provider.class, id);
            if (provider != null) {
                session.delete(provider);
                System.out.println("🗑️ Provider deleted: " + provider.getName());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
