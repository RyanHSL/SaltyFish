package com.saltyFish.user.service.impl;

import com.saltyFish.user.criteria.BooleanCondition;
import com.saltyFish.user.criteria.NumberCondition;
import com.saltyFish.user.criteria.StringCondition;
import com.saltyFish.user.criteria.TimeCondition;
import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.dto.BookingDetailsDto;
import com.saltyFish.user.dto.PersonalServiceDto;
import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import com.saltyFish.user.dto.userDto.UserDetails;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.exception.ResourceNotFoundException;
import com.saltyFish.user.exception.UserAlreadyExistsException;
import com.saltyFish.user.lookups.Gender;
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

import java.util.ArrayList;
import java.util.List;

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
//        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
//        userDto.setPassword(encodedPassword);
        User user = UserMapper.maptoUser(userDto, new User());
        User savedUser = userDAO.save(user);
        return mapToUserDetails(savedUser);
    }

    @Override
    public UserDto findUserBykeycloakId(String keycloakId) {
        if (keycloakId == null || keycloakId.isEmpty()) {
            throw new IllegalArgumentException("KeycloakId cannot be null or empty.");
        }
        User user = userDAO.findUserBykeycloakId(keycloakId);
        return UserMapper.mapToUserDto(user, new UserDto());
    }

    @Override
    public UserDetails findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null.");
        }
        User user = userDAO.findById(userId);
        return mapToUserDetails(user);
    }

    @Override
    public UserDetails findUserByUsername(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        User user = userDAO.findUsersByUsername(userName).orElse(null);
        return mapToUserDetails(user);
    }

    @Override
    public UserDetails findUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        User user = userDAO.findUsersByEmail(email).orElse(null);
        return mapToUserDetails(user);
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

    @Override
    public List<UserDetails> scoreAndFilterUsers(List<Condition<?>> conditions, Integer genderId, Integer roleId, double cutoffScore) {
        List<User> users = new ArrayList<>();
        if (genderId != null && roleId != null) {
            users = userDAO.findUserByGenderAndRole(genderId, roleId);
        }
        else if (genderId != null) {
            users = userDAO.findUserByGender(genderId);
        }
        else if (roleId != null) {
            users = userDAO.findUserByRole(roleId);
        }
        else {
            users = userDAO.findAll();
        }
        if (users.isEmpty()) {
            return new ArrayList<>();
        }

        List<UserDetails> scoredUsers = new ArrayList<>();

        for (User user : users) {
            double score = calculateScore(user, conditions);
            if (score >= cutoffScore) {
                UserDetails userDetail = mapToUserDetails(user); // May add score later
                scoredUsers.add(userDetail);
            }
        }

        return scoredUsers;
    }

    private double calculateScore(User user, List<Condition<?>> conditions) {
        double score = 0.0;
        if (conditions == null || conditions.isEmpty()) {
            return score;
        }

        for (Condition<?> condition : conditions) {
            switch (condition) {
                case NumberCondition numberCondition -> {
                    if (numberCondition.getNumericalConditionName().equals("serviceRating")) {
                        score += numberCondition.getScore(user, user.getRequesterProfile(), numberCondition.getNumericalConditionName());
                    }
                    else {
                        score += numberCondition.getScore(user, user.getRequesterProfile(), numberCondition.getNumericalConditionName());
                    }
                }
                case StringCondition stringCondition -> {
                    score += stringCondition.getScore(user, user.getRequesterProfile(), stringCondition.getStringAttributeName());
                }
                case BooleanCondition booleanCondition -> {
                    score += booleanCondition.getScore(user, user.getRequesterProfile(), booleanCondition.getBooleanConditionName());
                }
                case TimeCondition timeCondition -> {
                    score += timeCondition.getScore(user, user.getRequesterProfile(), timeCondition.getDateTimeConditionName());
                }
                default -> {
                    throw new IllegalArgumentException("Unknown condition: " + condition.toString());
                }
            }
        }

        return score / conditions.size();
    }
}
