package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.userDto.ProviderDto;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.entity.Provider;
import com.saltyFish.user.exception.ResourceNotFoundException;
import com.saltyFish.user.exception.UserAlreadyExistsException;
import com.saltyFish.user.mapper.UserMapper;
import com.saltyFish.user.repository.ProviderDAO;
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
public class ProviderServiceImpl implements UserService {

    private UserDAO userDAO;

    private ProviderDAO providerDAO;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ProviderServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder, ProviderDAO providerDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.providerDAO = providerDAO;
    }

    @Override
    public ProviderDto registerUser(UserDto userDto) {
        if (providerDAO.findUsersByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists", userDto.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        ProviderDto providerDto = (ProviderDto) userDto;
        providerDto.setPassword(encodedPassword);
        Provider provider = UserMapper.mapToProvider(providerDto, new Provider());
        providerDAO.save(provider);
        return providerDto;
    }

    @Override
    public ProviderDto updateUser(UserDto userDto) {
        Provider provider = UserMapper.mapToProvider((ProviderDto) userDto, new Provider());
        providerDAO.save(provider);
        return (ProviderDto) userDto;
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            if (providerDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Provider", "id", userId.toString());
            }
            providerDAO.deleteByID(userId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateMembership(Long userId) {
        try {
            if (providerDAO.findById(userId) == null) {
                throw new ResourceNotFoundException("Provider", "id", userId.toString());
            }
            Provider provider = (Provider) providerDAO.findById(userId);
            provider.setMember(true);
            providerDAO.update(provider);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<UserDto> findAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Provider> providerPage = providerDAO.findAllProvidersPageable(pageable);
        Page<UserDto> providerDtoPage = providerPage.
                map(provider -> UserMapper.mapToProviderDto(provider, new ProviderDto()));
        return providerDtoPage;
    }

    public void promoteService(Service service) {

    }
}
