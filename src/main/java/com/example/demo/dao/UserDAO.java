package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

// https://www.journaldev.com/17379/jpa-entitymanager-hibernate -> Reference link if want more details

@Repository
public class UserDAO implements IUserDAO {

        @PersistenceContext
        EntityManager em;//JPA EntityManager is used to access a database in a particular application. It is used to manage persistent entity instances, to find entities by their primary key identity(As here is Username), and to query over all entities.

        @Override
        @Transactional
        public User getUser(String username) {
            return em.find(User.class, username); //Here entitymanager can find the username as it has the capability to find an entity by unique identifies
        }


        @Override
        @Transactional
        public List<User> getUsers() {
            List<User> resultList = em.createQuery("FROM user_tbl", User.class).getResultList();
            return resultList;
        }

        @Override
        @Transactional
        public void createUser(User user) {
            em.persist(user);//Make an instance managed and persistent.
        }

        @Override
        @Transactional
        public void updateUser(User user) {
            em.merge(user);//Merge the state of the given entity into the current persistence context.
        }

        @Override
        @Transactional
        public void deleteUser(String username) {
            User user = this.getUser(username);
            em.remove(user);//Remove the entity instance.
        }
}
