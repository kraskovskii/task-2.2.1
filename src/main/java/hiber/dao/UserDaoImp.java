package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(Car car) {
        User user;
        String hql = "From User where car.model =: paramModel and  car.series =: paramSeries";
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setParameter("paramModel", car.getModel());
        query.setParameter("paramSeries", car.getSeries());
        try{
            return (User) query.getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println("Users do not have a car" + car + " ");
        }
        return null;
    }
}
