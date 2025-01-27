package com.saltyFish.appointment.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name="loans", url = "http://user:8090",fallback = UserFallback.class)
public interface UserFeignClient {

//    @GetMapping(value = "/api/fetch",consumes = "application/json")
//    public ResponseEntity<UserDTO> fetchLoanDetails(@RequestHeader("saltyFish-correlation-id")
//                                                         String correlationId, @RequestParam String mobileNumber);

}
