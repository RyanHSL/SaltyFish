package com.saltyFish.appointment.repository;

import com.saltyFish.appointment.entity.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
     **/
    public List<Appointment> findByCustomerId(Long customerId) {
        TypedQuery<Appointment> query = entityManager.createQuery(
                "SELECT a FROM Appointmentmet a WHERE customerId = :customerId", Appointment.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
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
