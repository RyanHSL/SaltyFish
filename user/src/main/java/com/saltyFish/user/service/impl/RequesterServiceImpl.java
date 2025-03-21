package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.userDto.RequesterDto;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.Requester;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.exception.ResourceNotFoundException;
import com.saltyFish.user.exception.UserAlreadyExistsException;
import com.saltyFish.user.repository.RequesterDAO;
import com.saltyFish.user.repository.UserDAO;
import com.saltyFish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.saltyFish.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class RequesterServiceImpl implements UserService {

    protected UserDAO userDAO;

    private RequesterDAO requesterDAO;

    protected BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RequesterServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder, RequesterDAO requesterDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.requesterDAO = requesterDAO;
    }

    @Override
    public RequesterDto registerUser(UserDto requesterDto) {
        if (requesterDAO.findUsersByEmail(requesterDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists", requesterDto.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(requesterDto.getPassword());
        Requester requester = UserMapper.maptoRequester((RequesterDto) requesterDto, new Requester());
        requester.setPassword(encodedPassword);
        requesterDAO.save(requester);
        return (RequesterDto) requesterDto;
    }

    @Override
    public RequesterDto updateUser(UserDto requesterDto) {
        Requester requester = UserMapper.maptoRequester((RequesterDto) requesterDto, new Requester());
        requesterDAO.update(requester);

        return (RequesterDto) requesterDto;
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            if (requesterDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Requester", "id", userId.toString());
            }
            requesterDAO.deleteByID(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateMembership(Long userId) {
        try {
            if (requesterDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Requester", "id", userId.toString());
            }
            Requester requester = (Requester) requesterDAO.findById(userId);
            requester.setMember(true);
            requesterDAO.update(requester);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<UserDto> findAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Requester> requesterPage = requesterDAO.findAllRequestersPageable(pageable);
        Page<UserDto> requesterDtoPage = requesterPage.
                map(requester -> UserMapper.mapToRequesterDto(requester, new RequesterDto()));
        return requesterDtoPage;
    }

}
