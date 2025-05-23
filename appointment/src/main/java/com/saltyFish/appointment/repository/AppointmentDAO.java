package com.saltyFish.appointment.repository;

import com.saltyFish.appointment.entity.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentDAO extends BaseDAO<Appointment, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    public AppointmentDAO() {
        super(Appointment.class);
    }

    /**
     * Find appointment by customer id
     * @param customerId
     * @return A list of appointments
     **/
    public List<Appointment> findByCustomerId(Long customerId) {
        TypedQuery<Appointment> query = entityManager.createQuery(
                "SELECT a FROM Appointmentmet a WHERE customerId = :customerId", Appointment.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    /**
     * Find all appointments by service owner id
     * @param serviceOwnerId
     * @return A list of appointments
     */
    public List<Appointment> findByServiceOwnerId(Long serviceOwnerId) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.serviceOwnerId = :serviceOwnerId", Appointment.class);
        query.setParameter("serviceOwnerId", serviceOwnerId);
        return query.getResultList();
    }

    /**
     * Find all appointments by service owner id and customer id
     * @param serviceOwnerId
     * @param customerId
     * @return A list of appointments
     */
    public List<Appointment> findByServiceOwnerIdAndCustomerId(Long serviceOwnerId, Long customerId) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.serviceOwnerId = :serviceOwnerId AND a.customerId = :customerId", Appointment.class);
        query.setParameter("serviceOwnerId", serviceOwnerId);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    /**
     * Find all appointments with pagination
     */
    public Page<Appointment> findAllAppointmentsPageable(Pageable pageable) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a ORDER BY a." + pageable.getSort().toString().replace(": ", " "), Appointment.class);
        query.setMaxResults(pageable.getPageSize());
        List<Appointment> appointments = query.getResultList();
        long total = count();
        return new PageImpl<>(appointments, pageable, total);
    }

    /**
     *
     * @param customerId
     * @return A list of appointments
     */
    public List<Appointment> findAppointmentsByCustomerId(Long customerId) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.customerId = :customerId", Appointment.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    /**
     * Find appointment by customer id with pagination and sorting
     **/
    public Page<Appointment> findByCustomerIdPageable(Long customerId, Pageable pageable) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a WHERE customerId = :customerId ORDER BY a." + pageable.getSort().toString().replace(": ", " "), Appointment.class);
        query.setParameter("customerId", customerId);
        query.setMaxResults(pageable.getPageSize());
        List<Appointment> appointments = query.getResultList();
        long total = countAppointments(customerId);
        return new PageImpl<>(appointments, pageable, total);
    }

    /**
     * Find appointment by confirmation number
    **/
    public Appointment findByConfirmationNumber(String confirmationNumber) {
        TypedQuery<Appointment> query = entityManager
                .createQuery("SELECT a FROM Appointment a WHERE a.confirmationNumber = :confirmationNumber", Appointment.class);
        query.setParameter("confirmationNumber", confirmationNumber);
        return query.getSingleResult();
    }

    /**
     * Delete appointment by confirmation number
     **/
    @Transactional
    @Modifying
    public int deleteByConfirmationNumber(String confirmationNumber) {
        return entityManager.createQuery("DELETE FROM Appointment a WHERE a.confirmationNumber = :confirmationNumber")
                .setParameter("confirmationNumber", confirmationNumber)
                .executeUpdate();
    }

    /**
     * Find appointments in a certain time period
     **/
    public List<Appointment> findAllAppointmentWithInThisTime(LocalDateTime startTime, LocalDateTime endTime) {
        TypedQuery<Appointment> query = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.startTime >= :startTime AND a.endTime <= :endTime", Appointment.class);
        query.setParameter("startTime", startTime).setParameter("endTime", endTime);
        return query.getResultList();
    }

    /**
     * Count all appointments of current user
     **/
    public Integer countAppointments(Long userId) {
        return entityManager.createQuery("SELECT COUNT(a) FROM Appointment a WHERE a.customerId = :userId", Integer.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
