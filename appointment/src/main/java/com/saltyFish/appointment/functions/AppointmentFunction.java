package com.saltyFish.appointment.functions;

import com.saltyFish.appointment.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AppointmentFunction {

    private static final Logger log = LoggerFactory.getLogger(AppointmentFunction.class);

    @Bean
    public Consumer<Long> updateCommunication(AppointmentService appointmentService) {
        return appointmentNumber->{
          log.info("Updating Communication status for the appointment id: " + appointmentService.toString());
            appointmentService.updateCommunicationStatus(appointmentNumber);
        };
    }
}
