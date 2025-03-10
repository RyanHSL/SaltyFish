package com.saltyFish.user.dto;

/**
 *
 * @param accountNumber
 * @param email
 * @param mobileNumber
 */
public record AppointmentMsgDto(Long accountNumber, String email, String mobileNumber) {
}
