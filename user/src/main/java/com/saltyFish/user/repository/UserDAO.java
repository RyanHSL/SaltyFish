package com.saltyFish.user.repository;

import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Role;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO extends BaseDAO<User, Long> {

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
                    createQuery("SELECT u FROM user u where username = :username AND isActive = true", User.class)
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
                    createQuery("SELECT u FROM user u where email = :email AND isActive = true", User.class)
                    .setParameter("email", email).getSingleResult();
            return Optional.of(user);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find all membered users
     * @return a list of users
     */
    public List<User> findMemberedUsers() {
        return entityManager.
                createQuery("SELECT u FROM user u where isMember = true AND isActive = true", User.class)
                .getResultList();
    }

    /**
     * fina all non-membered users
     * @return a list of users
     */
    public List<User> findNonMemberedUsers() {
        return entityManager.
                createQuery("SELECT u FROM user u where isMember = false AND isActive = true", User.class)
                .getResultList();
    }

    /**
     * count all membered users
     * @return the number of membered users
     */
    public long countMemberedUsers() {
        return entityManager.createQuery("SELECT COUNT(u) FROM user u where isMember = true AND isActive = true", Long.class)
                .getSingleResult();
    }

    /**
     * count all non-membered users
     * @return the number of non-membered users
     */
    public long countNonMemberedUsers() {
        return entityManager.createQuery("SElECT COUNT(u) FROM user u WHERE isMember = false AND isActive = true", Long.class)
                .getSingleResult();
    }

    /**
     *
     * @param pageable, role
     * @return a list of users based on the role type
     */
    public Page<User> findAllUsersPageable(Pageable pageable, Role role) {
        Sort sort = pageable.getSort();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.role = :role AND u.isMember = :isMember AND isActive = true", User.class);

        switch (role) {
            case Role.REQUESTER:
                query.setParameter("role", Role.REQUESTER.getId())
                        .setParameter("isMember", false);
                break;
            case Role.SERVICE_PROVIDER:
                query.setParameter("role", Role.SERVICE_PROVIDER.getId())
                        .setParameter("isMember", false);
                break;
            case Role.MEMBERED_REQUESTER:
                query.setParameter("role", Role.MEMBERED_REQUESTER.getId())
                        .setParameter("isMember", true);
                break;
            case Role.MEMBERED_SERVICE_PROVIDER:
                query.setParameter("role", Role.MEMBERED_SERVICE_PROVIDER.getId())
                        .setParameter("isMember", true);
                break;
            default:
                return null;
        }
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> users = query.getResultList();
        long total = count();
        return new PageImpl<>(users, pageable, total);
    }

    public Page<User> findAllUsersPageable(Pageable pageable) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.isActive = true", User.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> users = query.getResultList();
        Long total = count();
        return new PageImpl<>(users, pageable, total);
    }

    public User findUserBykeycloakId(String keycloakId) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.keycloakId = :keycloakId", User.class);
        query.setParameter("keycloakId", keycloakId);
        return query.getSingleResult();
    }

    public List<User> findUserByGenderAndRole(Integer genderId, Integer roleId) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.genderId = :genderId AND u.roleId = :roleId AND u.isActive = true", User.class);
        query.setParameter("genderId", genderId);
        query.setParameter("roleId", roleId);
        return query.getResultList();
    }

    public List<User> findUserByGender(Integer genderId) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.genderId = :genderId AND u.isActive = true", User.class);
        query.setParameter("genderId", genderId);
        return query.getResultList();
    }

    public List<User> findUserByRole(Integer roleId) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM user u WHERE u.roleId = :roleId AND u.isActive = true", User.class);
        query.setParameter("roleId", roleId);
        return query.getResultList();
    }

}
