package org.rubatophil.www.api.service.mapping;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.type.Experience;
import org.rubatophil.www.api.repository.mapping.ApplicantExperienceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantExperienceService {

    private final ApplicantExperienceRepository applicantExperienceRepository;

    public ApplicantExperience getApplicantExperience(Experience experience) { return this.applicantExperienceRepository.findByExperience(experience); }
}
