package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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

        return sessionFactory
                .getCurrentSession()
                .createQuery("from User")
                .getResultList();
    }

    @Override
    public User getUser(Car car) {

        String hql = "From User where car.model =: paramModel and  car.series =: paramSeries";

        try {
            return sessionFactory
                    .openSession()
                    .createQuery(hql, User.class)
                    .setParameter("paramModel", car.getModel())
                    .setParameter("paramSeries", car.getSeries())
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Users don't have a car" + car + " ");
        }
        return null;
    }
}
