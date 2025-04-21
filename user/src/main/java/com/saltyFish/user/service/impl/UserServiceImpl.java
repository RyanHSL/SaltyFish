package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.BookingDetailsDto;
import com.saltyFish.user.dto.PersonalServiceDto;
import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import com.saltyFish.user.dto.userDto.UserDetails;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.exception.ResourceNotFoundException;
import com.saltyFish.user.exception.UserAlreadyExistsException;
import com.saltyFish.user.lookups.Role;
import com.saltyFish.user.mapper.UserMapper;
import com.saltyFish.user.mapper.UserProfileMapper;
import com.saltyFish.user.repository.ProviderProfileDAO;
import com.saltyFish.user.repository.RequesterProfileDAO;
import com.saltyFish.user.repository.UserDAO;
import com.saltyFish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RequesterProfileDAO requesterProfileDAO;
    private ProviderProfileDAO providerProfileDAO;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RequesterProfileDAO requesterProfileDAO, ProviderProfileDAO providerProfileDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.requesterProfileDAO = requesterProfileDAO;
        this.providerProfileDAO = providerProfileDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails registerUser(UserDto userDto) {
        if (userDAO.findUsersByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists", userDto.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        User user = UserMapper.maptoUser(userDto, new User());
        User savedUser = userDAO.save(user);
        return mapToUserDetails(savedUser);
    }

    @Override
    public UserDetails updateUser(UserDto userDto) {
        User user = UserMapper.maptoUser(userDto, new User());
        User savedUser = userDAO.save(user);
        return mapToUserDetails(savedUser);
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            if (userDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Provider", "id", userId.toString());
            }
            userDAO.deleteById(userId);
//            requesterProfileDAO.deleteById(userId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateMembership(Long userId) {
        try {
            if (userDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Provider", "id", userId.toString());
            }
            User provider = userDAO.findById(userId);
            provider.setMember(true);
//            userDAO.update(provider);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<UserDetails> findAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> providerPage = userDAO.findAllUsersPageable(pageable);
        Page<UserDetails> userDetailsPage = providerPage.
                map(provider -> mapToUserDetails(provider));
        return userDetailsPage;
    }

    @Override
    public Page<UserDetails> findAllUsers(Role role, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> userPage = userDAO.findAllUsersPageable(pageable, role);
        Page<UserDetails> userDetailsPage = userPage.
                map(user -> mapToUserDetails(user));
        return userDetailsPage;
    }

    public void promoteService(PersonalServiceDto personalServiceDto) {

    }

    public void listService(PersonalServiceDto personalServiceDto) {

    }

    public void acceptBooking(BookingDetailsDto bookingDetailsDto) {

    }

    private UserDetails mapToUserDetails(User user) {
        ProviderProfileDto providerProfileDto = UserProfileMapper.mapToProviderProfileDto(user.getProviderProfile(), new ProviderProfileDto());
        RequesterProfileDto requesterProfileDto = UserProfileMapper.mapToRequesterProfileDto(user.getRequesterProfile(), new RequesterProfileDto());
        UserDetails userDetails = new UserDetails(user.getUsername(), user.getEmail(), user.getMember(), user.getActive(), user.getStartDate(), user.getExpiryDate(), user.getLevel(), requesterProfileDto, providerProfileDto);
        return userDetails;
    }
}
