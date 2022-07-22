package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.ApplyCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyCampaignRepository extends JpaRepository<ApplyCampaign, Long> {

    ApplyCampaign findTopByOrderByGenerationDesc();
}
