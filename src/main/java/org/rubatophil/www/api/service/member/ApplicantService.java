package org.rubatophil.www.api.service.member;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.repository.member.ApplicantRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private ApplicantRepository applicantRepository;

    public Applicant getApplicant(String name, String studentId) { return this.applicantRepository.findByNameAndStudentId(name, studentId); }
}
