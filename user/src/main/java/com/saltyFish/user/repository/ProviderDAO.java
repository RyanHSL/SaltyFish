package com.saltyFish.user.repository;

import com.saltyFish.user.entity.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProviderDAO extends UserDAO {

    /**
     *
     * @param pageable
     * @return a list of users
     */
    public Page<Provider> findAllProvidersPageable(Pageable pageable) {
        TypedQuery<Provider> query = entityManager.createQuery("SELECT p FROM Provider p", Provider.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Provider> providers = query.getResultList();
        long total = count();
        return new PageImpl<>(providers, pageable, total);
    }
}
