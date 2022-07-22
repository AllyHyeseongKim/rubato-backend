package org.rubatophil.www.api.repository.mapping;

import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.type.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantExperienceRepository extends JpaRepository<ApplicantExperience, Long> {

    ApplicantExperience findByExperience(Experience experience);
}
