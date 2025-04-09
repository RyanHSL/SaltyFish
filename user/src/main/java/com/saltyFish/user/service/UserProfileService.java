package com.saltyFish.user.service;

import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.dto.userDto.RequesterProfileDto;
import org.springframework.data.domain.Page;

public interface UserProfileService {

    /**
     *
     * @param requesterProfileDto
     * @return registered requester profile
     */
    RequesterProfileDto createRequesterProfile(RequesterProfileDto requesterProfileDto);

    /**
     *
     * @param requesterProfileDto, requesterProfileId
     * @return updated requester profile
     */
    RequesterProfileDto updateRequester(Long requesterProfileId, RequesterProfileDto requesterProfileDto);

    /**
     *
     * @param userId
     * @return flag if requesterProfile is deleted
     */
    boolean deleteRequesterProfile(Long userId);

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortOrder
     * @return list of requester profiles
     */
    Page<RequesterProfileDto> findAllRequesterProfiles(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    /**
     *
     * @param providerProfileDto
     * @return registered provider profile
     */
    ProviderProfileDto createProviderProfile(ProviderProfileDto providerProfileDto);

    /**
     *
     * @param providerProfileDto
     * @return updated provider profile
     */
    ProviderProfileDto updateProvider(ProviderProfileDto providerProfileDto);

    /**
     *
     * @param userId
     * @return flag if providerProfile is deleted
     */
    boolean deleteProviderProfile(Long userId);

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortOrder
     * @return list of provider profiles
     */
    Page<ProviderProfileDto> findAllProviderProfiles(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
