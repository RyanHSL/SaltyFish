package com.saltyFish.user.repository;

import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RequesterProfileDAO extends BaseDAO<RequesterProfile, Long> {

    public RequesterProfileDAO() {
        super(RequesterProfile.class);
    }

    /**
     *
     * @param pageable
     * @return requester profiles paged
     */
    public Page<RequesterProfile> findAllRequestersPageable(Pageable pageable) {
        TypedQuery<RequesterProfile> query = entityManager.createQuery("SELECT r FROM requesterProfile r" + pageable.getSort().toString().replace(": ", " "), RequesterProfile.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<RequesterProfile> requesterProfileList = query.getResultList();
        long total = count();
        return new PageImpl<>(requesterProfileList, pageable, total);
    }

    /**
     *
     * @param rating
     * @param pageable
     * @return requester profiles paged
     */
    public Page<RequesterProfile> findRequesterProfilesWithRatingHigherThan(double rating, Pageable pageable) {
        TypedQuery<RequesterProfile> query = entityManager.createQuery("SELECT r FORM requesterProfile r WHERE r.rating >= :rating " + pageable.getSort().toString().replace(": ", " "), RequesterProfile.class);
        query.setParameter("rating", rating);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<RequesterProfile> requesterProfileList = query.getResultList();
        long total = count();
        return new PageImpl<>(requesterProfileList, pageable, total);
    }

    /**
     * find users by phone number
     * @param phoneNumber
     * @return a list of RequesterProfile
     */
    public Optional<RequesterProfile> findRequesterProfileByPhoneNumber(String phoneNumber) {
        try {
            RequesterProfile requesterProfile = entityManager.
                    createQuery("SELECT r FROM requesterProfile r where phoneNumber = :phoneNumber AND isActive = true", RequesterProfile.class)
                    .setParameter("phoneNumber", phoneNumber).getSingleResult();
            return Optional.of(requesterProfile);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find users by first name
     * @param firstName
     * @return a list of RequesterProfile
     */
    public Optional<List<RequesterProfile>> findRequesterProfilesByFirstName(String firstName) {
        try {
            List<RequesterProfile> requesterProfiles = entityManager.
                    createQuery("SELECT r FROM requesterProfile r where firstName = :firstName AND isActive = true", RequesterProfile.class)
                    .setParameter("firstName", firstName).getResultList();
            return Optional.of(requesterProfiles);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * find users by last name
     * @param lastName
     * @return a list of RequesterProfile
     */
    public Optional<List<RequesterProfile>> findRequesterProfilesByLastName(String lastName) {
        try {
            List<RequesterProfile> requesterProfiles = entityManager.
                    createQuery("SELECT r FROM requesterProfile r WHERE lastName = :lastName AND isActive = true", RequesterProfile.class)
                    .setParameter("lastName", lastName).getResultList();
            return Optional.of(requesterProfiles);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
