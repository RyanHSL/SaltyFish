package com.saltyFish.appointment.client;

import com.saltyFish.appointment.external.PersonalService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "personalServicems",
        url = "${personalServicems.url}")
public interface PersonalServiceClient {

    @GetMapping("/personalService/{id}")
    PersonalService getPersonalService(@PathVariable("id") Long id);
}
