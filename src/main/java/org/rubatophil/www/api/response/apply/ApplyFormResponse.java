package org.rubatophil.www.api.response.apply;

import lombok.*;
import org.rubatophil.www.api.domain.Apply;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.type.Address;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyFormResponse {

    private String name;
    private String department;
    private String studentId;
    private String birth;
    private String phoneNumber;
    private Address address;
    private String introduction;
    private Map<String, Integer> experiences;
    private String preferredInstrument;
    private String documentStatus;

    @Builder
    public ApplyFormResponse(String name, String department, String studentId, String birth, String phoneNumber, Address address, String introduction, Map<String, Integer> experiences, String preferredInstrument, String documentStatus) {
        this.name = name;
        this.department = department;
        this.studentId = studentId;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.introduction = introduction;
        this.experiences = experiences;
        this.preferredInstrument = preferredInstrument;
        this.documentStatus = documentStatus;
    }

    public static ApplyFormResponse of(Apply apply) {

        Map<String, Integer> experienceList = new LinkedHashMap<>();

        for (ApplicantExperience applicantExperience : apply.getApplicant().getApplicantExperiences()) {
            experienceList.put(applicantExperience.getExperience().getInstrument().toString(), applicantExperience.getExperience().getExperienceYear());
        }

        String applicantDepartment = apply.getApplicant().getDepartment().getDepartment();
        if (applicantDepartment == null) {
            applicantDepartment = apply.getApplicant().getDepartment().getSchool();
            if (applicantDepartment == null) {
                applicantDepartment = apply.getApplicant().getDepartment().getCollege();
            }
        }

        String[] birth = new String[3];
        birth[0] = Integer.toString(apply.getApplicant().getBirth().getYear());
        birth[1] = Integer.toString(apply.getApplicant().getBirth().getMonthValue());
        birth[2] = Integer.toString(apply.getApplicant().getBirth().getDayOfMonth());

        return builder()
                .name(apply.getApplicant().getName())
                .birth(String.join(".", birth))
                .phoneNumber(apply.getApplicant().getPhoneNumber())
                .address(apply.getApplicant().getAddress())
                .department(applicantDepartment)
                .studentId(apply.getApplicant().getStudentId())
                .introduction(apply.getIntroduction())
                .experiences(experienceList)
                .preferredInstrument(apply.getPreferredInstrument().toString())
                .documentStatus(apply.getDocumentStatus().toString())
                .build();
    }
}
