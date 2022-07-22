package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Apply;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.repository.ApplyRepository;
import org.rubatophil.www.api.repository.mapping.ApplicantExperienceRepository;
import org.rubatophil.www.api.repository.member.ApplicantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantExperienceRepository applicantExperienceRepository;

    public Apply getApply(String studentId) { return this.applyRepository.findByStudentId(studentId); }

    public void addNewApply(Apply apply, Applicant applicant, List<ApplicantExperience> applicantExperiences) {
        this.applicantRepository.save(applicant);
        this.applicantExperienceRepository.saveAll(applicantExperiences);
        this.applyRepository.save(apply);
    }
}
