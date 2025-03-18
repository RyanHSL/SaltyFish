package com.saltyFish.user.repository;

import com.saltyFish.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO extends BaseDAO<User, Long> {

//    @PersistenceContext
//    private EntityManager entityManager;

    public UserDAO() {
        super(User.class);
    }

    /**
     * Find users by username
     * @param username
     * @return single user
     */
    public Optional<User> findUsersByUsername(String username) {
        try {
            User user = entityManager.
                    createQuery("SELECT u FROM user u where username = :username", User.class)
                    .setParameter("username", username).getSingleResult();
            return Optional.of(user);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Find users by email
     * @param email
     * @return a list of users
     */
    public Optional<User> findUsersByEmail(String email) {
        try {
            User user = entityManager.
                    createQuery("SELECT u FROM user u where email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
            return Optional.of(user);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find users by phone number
     * @param phoneNumber
     * @return a list of users
     */
    public Optional<User> findUsersByPhoneNumber(String phoneNumber) {
        try {
            User user = entityManager.
                    createQuery("SELECT u FROM user u where phoneNumber = :phoneNumber", User.class)
                    .setParameter("phoneNumber", phoneNumber).getSingleResult();
            return Optional.of(user);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find users by first name
     * @param firstName
     * @return a list of users
     */
    public Optional<List<User>> findUsersByFirstName(String firstName) {
        try {
            List<User> users = entityManager.
                    createQuery("SELECT u FROM user u where firstName = :firstName", User.class)
                    .setParameter("firstName", firstName).getResultList();
            return Optional.of(users);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find users by last name
     * @param lastName
     * @return a list of users
     */
    public List<User> findUsersByLastName(String lastName) {
        return entityManager.
                createQuery("SELECT u FROM user u where lastName = :lastName", User.class)
                .setParameter("lastName", lastName).getResultList();
    }

    /**
     * find all membered users
     * @return a list of users
     */
    public List<User> findMemberedUsers() {
        return entityManager.
                createQuery("SELECT u FROM user u where isMember = true", User.class)
                .getResultList();
    }

    /**
     * fina all non-membered users
     * @return a list of users
     */
    public List<User> findNonMemberedUsers() {
        return entityManager.
                createQuery("SELECT u FROM user u where isMember = false", User.class)
                .getResultList();
    }

    /**
     * count all membered users
     * @return the number of membered users
     */
    public long countMemberedUsers() {
        return entityManager.createQuery("SELECT COUNT(u) FROM user u where isMember = true", Long.class)
                .getSingleResult();
    }

    /**
     * count all non-membered users
     * @return the number of non-membered users
     */
    public long countNonMemberedUsers() {
        return entityManager.createQuery("SElECT COUNT(u) FROM user u WHERE isMember = false", Long.class)
                .getSingleResult();
    }

    /**
     *
     * @param pageable
     * @return a list of users
     */
    public Page<User> findAllUsersPageable(Pageable pageable) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u", User.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> users = query.getResultList();
        long total = count();
        return new PageImpl<>(users, pageable, total);
    }
}
