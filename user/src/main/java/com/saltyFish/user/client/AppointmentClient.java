package com.saltyFish.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "appointment", url = "${appointment.url}")
public class AppointmentClient {
}
