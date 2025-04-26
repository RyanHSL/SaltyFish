package com.saltyFish.user.service;

import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.dto.userDto.UserDetails;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.lookups.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    /**
     *
     * @param userDto
     * @return registered user details
     */
    UserDetails registerUser(UserDto userDto);

    /**
     *
     * @param keycloakId
     * @return user account details
     */
    UserDto findUserBykeycloakId(String keycloakId);

    /**
     *
     * @param userId
     * @return user details
     */
    UserDetails findUserById(Long userId);

    /**
     *
     * @param userName
     * @return user details
     */
    UserDetails findUserByUsername(String userName);

    /**
     *
     * @param email
     * @return user details
     */
    UserDetails findUserByEmail(String email);

    /**
     *
     * @param userDto
     * @return updated user details
     */
    UserDetails updateUser(UserDto userDto);

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

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortOrder
     * @return list of users
     */
    Page<UserDetails> findAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    /**
     * @param role
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortOrder
     * @return list of users based on role
     */
    Page<UserDetails> findAllUsers(Role role, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    /**
     * @param conditions
     * @param genderId
     * @param roleId
     * @param cutoffScore
     * @return list of users based on conditions
     */
    List<UserDetails> scoreAndFilterUsers(List<Condition<?>> conditions, Integer genderId, Integer roleId, double cutoffScore);
}
