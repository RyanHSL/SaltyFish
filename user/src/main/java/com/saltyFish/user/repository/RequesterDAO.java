package com.saltyFish.user.repository;

import com.saltyFish.user.entity.Requester;
import com.saltyFish.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequesterDAO extends UserDAO {

    /**
     *
     * @param pageable
     * @return a list of users
     */
    public Page<Requester> findAllRequestersPageable(Pageable pageable) {
        TypedQuery<Requester> query = entityManager.createQuery("SELECT u FROM user u", Requester.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Requester> requesters = query.getResultList();
        long total = count();
        return new PageImpl<>(requesters, pageable, total);
    }

//    /**
//     * count all requesters
//     * @return the number of requesters
//     */
//    public long countRequesters() {
//        return entityManager.createQuery("SELECT COUNT(r) FROM requester r", Long.class).getSingleResult();
//    }
//
//    /**
//     * find all non-membered requesters
//     * @return a list of requesters
//     */
//    public List<Requester> findNonMemberedRequesters() {
//        return entityManager.createQuery("SELECT r FROM requester r WHERE r.isMember = false", Requester.class)
//                .getResultList();
//    }
//
//    /**
//     * find all membered requesters
//     * @return a list of requesters
//     */
//    public List<Requester> findMemberedRequesters() {
//        return entityManager.createQuery("SELECT r FROM requester r WHERE r.isMember = true", Requester.class)
//                .getResultList();
//    }
//
//    /**
//     * count all non-membered requesters
//     * @return the number of non-membered requesters
//     */
//    public List<Requester> countNonMemberedRequesters() {
//        return entityManager.createQuery("SELECT COUNT(r) FROM requester r WHERE r.isMember = false", Requester.class)
//                .getResultList();
//    }
}
