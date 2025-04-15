package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.mapper.UserProfileMapper;
import com.saltyFish.user.repository.ProviderProfileDAO;
import com.saltyFish.user.repository.RequesterProfileDAO;
import com.saltyFish.user.repository.UserDAO;
import com.saltyFish.user.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserDAO userDAO;
    private RequesterProfileDAO requesterProfileDAO;
    private ProviderProfileDAO providerProfileDAO;

    public UserProfileServiceImpl(UserDAO userDAO, RequesterProfileDAO requesterProfileDAO, ProviderProfileDAO providerProfileDAO) {
        this.userDAO = userDAO;
        this.requesterProfileDAO = requesterProfileDAO;
        this.providerProfileDAO = providerProfileDAO;
    }

    @Override
    public RequesterProfileDto createRequesterProfile(RequesterProfileDto requesterProfileDto) {
        RequesterProfile requesterProfile = UserProfileMapper.mapToRequesterProfile(requesterProfileDto, new RequesterProfile());
        try {
            requesterProfileDAO.save(requesterProfile);
            return requesterProfileDto;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public RequesterProfileDto updateRequester(Long requesterProfileId, RequesterProfileDto requesterProfileDto) {
        if (requesterProfileDAO.findById(requesterProfileId) == null) {
            throw new EntityNotFoundException("Requester profile with id " + requesterProfileId + " does not exist.");
        }
        RequesterProfile requesterProfile = UserProfileMapper.mapToRequesterProfile(requesterProfileDto, new RequesterProfile());
        requesterProfile.setId(requesterProfileId);
        requesterProfileDAO.update(requesterProfile);
        User user = userDAO.findById(requesterProfileId);
        if (null == user) {
            throw new IllegalStateException("Cannot update a user profile without an existing user.");
        }
        user.setRequesterProfile(requesterProfile);
        userDAO.save(user);

        return requesterProfileDto;
    }

    @Override
    public boolean deleteRequesterProfile(Long userId) {
        if (requesterProfileDAO.findById(userId) != null) {
            try {
                requesterProfileDAO.deleteById(userId);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
        else {
            throw new EntityNotFoundException("Requester profile with id " + userId + " does not exist.");
        }
    }

    @Override
    public Page<RequesterProfileDto> findAllRequesterProfiles(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<RequesterProfile> requesterProfiles = requesterProfileDAO.findAllRequestersPageable(pageable);
        Page<RequesterProfileDto> requesterProfileDtos = requesterProfiles.map(requesterProfile -> UserProfileMapper.mapToRequesterProfileDto(requesterProfile, new RequesterProfileDto()));
        return requesterProfileDtos;
    }

    @Override
    public ProviderProfileDto createProviderProfile(ProviderProfileDto providerProfileDto) {
        return null;
    }

    @Override
    public ProviderProfileDto updateProvider(ProviderProfileDto providerProfileDto) {
        return null;
    }

    @Override
    public boolean deleteProviderProfile(Long userId) {
        return false;
    }

    @Override
    public Page<ProviderProfileDto> findAllProviderProfiles(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }
}
