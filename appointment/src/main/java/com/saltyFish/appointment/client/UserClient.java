package com.saltyFish.appointment.client;

import com.saltyFish.appointment.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user",
        url = "${user.url}")
public interface UserClient {

    @GetMapping("/api/v1/users/{id}")
    User getUser(@PathVariable("id") Long id);
}
