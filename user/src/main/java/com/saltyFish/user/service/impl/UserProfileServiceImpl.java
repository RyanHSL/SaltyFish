package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import com.saltyFish.user.service.UserProfileService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public RequesterProfileDto createRequesterProfile(RequesterProfileDto requesterProfileDto) {
        return null;
    }

    @Override
    public RequesterProfileDto updateRequester(RequesterProfileDto requesterProfileDto) {
        return null;
    }

    @Override
    public boolean deleteRequesterProfile(Long userId) {
        return false;
    }

    @Override
    public Page<RequesterProfileDto> findAllRequesterProfiles(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
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
