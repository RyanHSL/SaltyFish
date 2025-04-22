package com.saltyFish.user.mapper;

import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto) {
        if (user == null) return null;
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setMember(user.getMember());
        userDto.setLevel(user.getLevel());
        userDto.setStartDate(user.getStartDate());
        userDto.setExpiryDate(user.getExpiryDate());
        return userDto;
    }

    public static User maptoUser(UserDto userDto, User user) {
        if (user == null) return null;
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setMember(userDto.getMember());
        user.setLevel(userDto.getLevel());
        user.setStartDate(userDto.getStartDate());
        user.setExpiryDate(userDto.getExpiryDate());
        return user;
    }

}
