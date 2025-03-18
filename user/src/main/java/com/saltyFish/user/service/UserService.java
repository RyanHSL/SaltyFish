package com.saltyFish.user.service;

import com.saltyFish.user.dto.userDto.RequesterDto;
import com.saltyFish.user.dto.userDto.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {

    /**
     *
     * @param userDto
     * @return registered user details
     */
    UserDto registerUser(UserDto userDto);

    /**
     *
     * @param userDto
     * @return updated user details
     */
    UserDto updateUser(UserDto userDto);

    /**
     *
     * @param userId
     * @return flag if user is deleted
     */
    boolean deleteUser(Long userId);

    /**
     *
     * @param userId
     * @return flag if membership is activated
     */
    boolean activateMembership(Long userId);

}
