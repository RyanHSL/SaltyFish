package com.saltyFish.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "user")
@Getter
@Setter
public class UserContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}
