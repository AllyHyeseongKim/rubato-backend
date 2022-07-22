package org.rubatophil.www.api.response.member;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.Address;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantResponse {
    private String name;
    private String birth;
    private String phoneNumber;
    private Address address;
    private String department;
    private String studentId;
    private Map<String, Integer> applicantExperiences;

    @Builder
    public ApplicantResponse(String name, String birth, String phoneNumber, Address address, String department, String studentId, Map<String, Integer> applicantExperiences) {
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
        this.studentId = studentId;
        this.applicantExperiences = applicantExperiences;
    }

    public static ApplicantResponse of(Applicant applicant) {

        Map<String, Integer> experienceList = new LinkedHashMap<>();

        for (ApplicantExperience applicantExperience : applicant.getApplicantExperiences()) {
            experienceList.put(applicantExperience.getExperience().getInstrument().toString(), applicantExperience.getExperience().getExperienceYear());
        }

        String applicantDepartment = applicant.getDepartment().getDepartment();
        if (applicantDepartment == null) {
            applicantDepartment = applicant.getDepartment().getSchool();
            if (applicantDepartment == null) {
                applicantDepartment = applicant.getDepartment().getCollege();
            }
        }

        String[] birth = new String[3];
        birth[0] = Integer.toString(applicant.getBirth().getYear());
        birth[1] = Integer.toString(applicant.getBirth().getMonthValue());
        birth[2] = Integer.toString(applicant.getBirth().getDayOfMonth());

        return builder()
                .name(applicant.getName())
                .birth(String.join(".", birth))
                .phoneNumber(applicant.getPhoneNumber())
                .address(applicant.getAddress())
                .department(applicantDepartment)
                .studentId(applicant.getStudentId())
                .applicantExperiences(experienceList)
                .build();
    }
}
