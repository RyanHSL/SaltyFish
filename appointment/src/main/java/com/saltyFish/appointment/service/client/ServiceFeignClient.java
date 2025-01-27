package com.saltyFish.appointment.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="service", url = "http://service:9000", fallback = ServiceFallback.class)
public interface ServiceFeignClient {

//    @GetMapping(value = "/api/fetch",consumes = "application/json")
//    public ResponseEntity<ServiceDto> fetchCardDetails(@RequestHeader("saltyFish-correlation-id")
//                                                         String correlationId, @RequestParam String mobileNumber);

}
