package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> listUsersWithCar(String model_car, int series_car) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().
              createQuery("select e from User e where e.car.model =:model and e.car.series=:series", User.class)
              .setParameter("model", model_car).setParameter("series", series_car);
      return query.getResultList();
   }

}
