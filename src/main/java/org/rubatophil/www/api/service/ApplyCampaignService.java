package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.ApplyCampaign;
import org.rubatophil.www.api.repository.ApplyCampaignRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyCampaignService {

    private final ApplyCampaignRepository applyCampaignRepository;

    public ApplyCampaign getCurrentApplyCampaign() { return this.applyCampaignRepository.findTopByOrderByGenerationDesc(); }


}
