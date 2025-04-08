package com.saltyFish.user.repository;

import com.saltyFish.user.entity.ProviderProfile;
import com.saltyFish.user.entity.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProviderProfileDAO extends BaseDAO<ProviderProfile, Long> {

    public ProviderProfileDAO() {
        super(ProviderProfile.class);
    }

    /**
     *
     * @param pageable
     * @return provider profiles paged
     */
    public Page<ProviderProfile> findAllProviderPageable(Pageable pageable) {
        TypedQuery<ProviderProfile> query = entityManager.createQuery("SELECT p FROM providerProfile p" + pageable.getSort().toString().replace(": ", " "), ProviderProfile.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<ProviderProfile> providerProfileList = query.getResultList();
        long total = count();
        return new PageImpl<>(providerProfileList, pageable, total);
    }

    /**
     *
     * @param rating
     * @param pageable
     * @return
     */
    public Page<ProviderProfile> findProviderWithRatingHigherThan(double rating, Pageable pageable) {
        TypedQuery<ProviderProfile> query = entityManager.createQuery("SELECT p FORM providerProfile p WHERE r.serviceRating >= :rating " + pageable.getSort().toString().replace(": ", " "), ProviderProfile.class);
        query.setParameter("rating", rating);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<ProviderProfile> providerProfileList = query.getResultList();
        long total = count();
        return new PageImpl<>(providerProfileList, pageable, total);
    }

    /**
     * find users by phone number
     * @param phoneNumber
     * @return a list of users
     */
    public Optional<User> findProviderProfileByPhoneNumber(String phoneNumber) {
        try {
            User user = entityManager.
                    createQuery("SELECT u FROM user u where phoneNumber = :phoneNumber AND isActive = true", User.class)
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
    public Optional<List<User>> findProviderProfilesByFirstName(String firstName) {
        try {
            List<User> users = entityManager.
                    createQuery("SELECT u FROM user u where firstName = :firstName AND isActive = true", User.class)
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
    public Optional<List<ProviderProfile>> findProviderProfilesByLastName(String lastName) {
        try {
            List<ProviderProfile> providerProfiles = entityManager.
                    createQuery("SELECT p FROM providerProfile p WHERE lastName = :lastName AND isActive = true", ProviderProfile.class)
                    .setParameter("lastName", lastName).getResultList();
            return Optional.of(providerProfiles);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }


}
