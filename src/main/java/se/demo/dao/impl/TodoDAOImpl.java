package se.demo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import se.demo.dao.TodoDAO;
import se.demo.entity.Todo;

import java.util.List;
import java.util.Optional;

public class TodoDAOImpl implements TodoDAO {
    private SessionFactory sessionFactory;

    public TodoDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Todo todo) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(todo);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Todo> findById(Long id) {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.get(Todo.class, id));
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Todo> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Todo", Todo.class).list();
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void update(Todo todo) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(todo);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Todo todo) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(todo);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
