package com.saltyFish.user.mapper;

import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import com.saltyFish.user.entity.ProviderProfile;
import com.saltyFish.user.entity.RequesterProfile;

public class UserProfileMapper {

    public static RequesterProfileDto mapToRequesterProfileDto(RequesterProfile requesterProfile, RequesterProfileDto requesterProfileDto) {
        requesterProfileDto.setFirstName(requesterProfile.getFirstName());
        requesterProfileDto.setLastName(requesterProfile.getLastName());
        requesterProfileDto.setDateOfBirth(requesterProfile.getDateOfBirth());
        requesterProfileDto.setGender(requesterProfile.getGender());
        requesterProfileDto.setLast4SSN(requesterProfile.getLast4SSN());
        requesterProfileDto.setPhoneNumber(requesterProfile.getPhoneNumber());
        requesterProfileDto.setAddresses(requesterProfile.getAddresses());
        requesterProfileDto.setAppointments(requesterProfile.getAppointments());
        requesterProfileDto.setBalance(requesterProfile.getBalance());
        requesterProfileDto.setRating(requesterProfile.getRating());
        requesterProfileDto.setReviews(requesterProfile.getReviews());
        return requesterProfileDto;
    }

    public static RequesterProfile mapToRequesterProfile(RequesterProfileDto requesterProfileDto, RequesterProfile requesterProfile) {
        requesterProfile.setFirstName(requesterProfileDto.getFirstName());
        requesterProfile.setLastName(requesterProfileDto.getLastName());
        requesterProfile.setDateOfBirth(requesterProfileDto.getDateOfBirth());
        requesterProfile.setGender(requesterProfileDto.getGender());
        requesterProfile.setLast4SSN(requesterProfileDto.getLast4SSN());
        requesterProfile.setPhoneNumber(requesterProfileDto.getPhoneNumber());
        requesterProfile.setAddresses(requesterProfileDto.getAddresses());
        requesterProfile.setAppointments(requesterProfileDto.getAppointments());
        requesterProfile.setBalance(requesterProfileDto.getBalance());
        requesterProfile.setRating(requesterProfileDto.getRating());
        requesterProfile.setReviews(requesterProfileDto.getReviews());
        return requesterProfile;
    }

    public static ProviderProfileDto mapToProviderProfileDto(ProviderProfile providerProfile, ProviderProfileDto providerProfileDto) {
        providerProfileDto.setGender(providerProfile.getGender());
        providerProfileDto.setDateOfBirth(providerProfile.getDateOfBirth());
        providerProfileDto.setLast4SSN(providerProfile.getLast4SSN());
        providerProfileDto.setAppointments(providerProfile.getAppointments());
        providerProfileDto.setAddresses(providerProfile.getAddresses());
        providerProfileDto.setBalance(providerProfile.getBalance());
        providerProfileDto.setReviews(providerProfile.getReviews());
        return providerProfileDto;
    }

    public static ProviderProfile mapToProviderProfile(ProviderProfileDto providerProfileDto, ProviderProfile providerProfile) {
        providerProfile.setGender(providerProfileDto.getGender());
        providerProfile.setDateOfBirth(providerProfileDto.getDateOfBirth());
        providerProfile.setLast4SSN(providerProfileDto.getLast4SSN());
        providerProfile.setAppointments(providerProfileDto.getAppointments());
        providerProfile.setAddresses(providerProfileDto.getAddresses());
        providerProfile.setBalance(providerProfileDto.getBalance());
        providerProfile.setReviews(providerProfileDto.getReviews());
        return providerProfile;
    }

}
