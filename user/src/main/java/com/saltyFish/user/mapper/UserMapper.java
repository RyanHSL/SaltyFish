package com.saltyFish.user.mapper;

import com.saltyFish.user.dto.userDto.ProviderDto;
import com.saltyFish.user.dto.userDto.RequesterDto;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.Provider;
import com.saltyFish.user.entity.Requester;
import com.saltyFish.user.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setActive(user.getActive());
        userDto.setMember(user.getMember());
        userDto.setLevel(user.getLevel());
        userDto.setStartDate(user.getStartDate());
        userDto.setExpiryDate(user.getExpiryDate());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddresses(user.getAddresses());
        return userDto;
    }

    public static User maptoUser(UserDto userDto, User user) {
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.getActive());
        user.setMember(userDto.getMember());
        user.setLevel(userDto.getLevel());
        user.setStartDate(userDto.getStartDate());
        user.setExpiryDate(userDto.getExpiryDate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddresses(userDto.getAddresses());
        return user;
    }

    public static Requester maptoRequester(RequesterDto requesterDto, Requester requester) {
        requester.setUsername(requesterDto.getUsername());
        requester.setPassword(requesterDto.getPassword());
        requester.setFirstName(requesterDto.getFirstName());
        requester.setLastName(requesterDto.getLastName());
        requester.setEmail(requesterDto.getEmail());
        requester.setActive(requesterDto.getActive());
        requester.setMember(requesterDto.getMember());
        requester.setLevel(requesterDto.getLevel());
        requester.setStartDate(requesterDto.getStartDate());
        requester.setExpiryDate(requesterDto.getExpiryDate());
        requester.setPhoneNumber(requesterDto.getPhoneNumber());
        requester.setAddresses(requesterDto.getAddresses());
        return requester;
    }

    public static RequesterDto mapToRequesterDto(Requester requester, RequesterDto requesterDto) {
        requesterDto.setUsername(requester.getUsername());
        requesterDto.setPassword(requester.getPassword());
        requesterDto.setFirstName(requester.getFirstName());
        requesterDto.setLastName(requester.getLastName());
        requesterDto.setEmail(requester.getEmail());
        requesterDto.setActive(requester.getActive());
        requesterDto.setMember(requester.getMember());
        requesterDto.setLevel(requester.getLevel());
        requesterDto.setStartDate(requester.getStartDate());
        requesterDto.setExpiryDate(requester.getExpiryDate());
        requesterDto.setPhoneNumber(requester.getPhoneNumber());
        requesterDto.setAddresses(requester.getAddresses());
        return requesterDto;
    }

    public static ProviderDto mapToProviderDto(Provider provider, ProviderDto providerDto) {
        providerDto.setUsername(provider.getUsername());
        providerDto.setPassword(provider.getPassword());
        providerDto.setFirstName(provider.getFirstName());
        providerDto.setLastName(provider.getLastName());
        providerDto.setEmail(provider.getEmail());
        providerDto.setActive(provider.getActive());
        providerDto.setMember(provider.getMember());
        providerDto.setLevel(provider.getLevel());
        providerDto.setStartDate(provider.getStartDate());
        providerDto.setExpiryDate(provider.getExpiryDate());
        providerDto.setPhoneNumber(provider.getPhoneNumber());
        providerDto.setAddresses(provider.getAddresses());
        providerDto.setRole(provider.getRole());
        providerDto.setRating(provider.getRating());
        return providerDto;
    }

    public static Provider mapToProvider(ProviderDto providerDto, Provider provider) {
        provider.setUsername(providerDto.getUsername());
        provider.setPassword(providerDto.getPassword());
        provider.setFirstName(providerDto.getFirstName());
        provider.setLastName(providerDto.getLastName());
        provider.setEmail(providerDto.getEmail());
        provider.setActive(providerDto.getActive());
        provider.setMember(providerDto.getMember());
        provider.setLevel(providerDto.getLevel());
        provider.setStartDate(providerDto.getStartDate());
        provider.setExpiryDate(providerDto.getExpiryDate());
        provider.setPhoneNumber(providerDto.getPhoneNumber());
        provider.setAddresses(providerDto.getAddresses());
        provider.setRole(providerDto.getRole());
        provider.setRating(providerDto.getRating());
        return provider;
    }
}
