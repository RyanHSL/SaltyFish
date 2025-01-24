package com.saltyFish.appointment.repository;

import com.saltyFish.appointment.entity.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByCustomerId(Long customerId);

    Optional<Appointment> findByConfirmationId(String confirmationId);

    @Transactional
    @Modifying
    void deleteByConfirmationId(String confirmationId);

}
