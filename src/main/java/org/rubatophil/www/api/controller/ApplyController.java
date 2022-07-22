package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Apply;
import org.rubatophil.www.api.domain.ApplyCampaign;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.DocumentStatus;
import org.rubatophil.www.api.domain.type.Experience;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.request.ApplyForm;
import org.rubatophil.www.api.response.apply.ApplyDurationResponse;
import org.rubatophil.www.api.response.apply.ApplyFormResponse;
import org.rubatophil.www.api.response.member.ApplicantResponse;
import org.rubatophil.www.api.service.ApplyCampaignService;
import org.rubatophil.www.api.service.ApplyService;
import org.rubatophil.www.api.service.member.ApplicantService;
import org.rubatophil.www.api.service.mapping.ApplicantExperienceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;
    private final ApplicantService applicantService;
    private final ApplyCampaignService applyCampaignService;
    private final ApplicantExperienceService applicantExperienceService;

    @GetMapping("/apply/check")
    public ApplyDurationResponse getApplyDurationInfo() {

        ApplyCampaign applyCampaignResult = this.applyCampaignService.getCurrentApplyCampaign();

        return ApplyDurationResponse.builder()
                .start(applyCampaignResult.getOpenedAt())
                .end(applyCampaignResult.getClosedAt())
                .status(applyCampaignResult.getStatus().toString())
                .build();
    }

    @GetMapping("/apply")
    public ApplicantResponse getApplicantInfo(@Valid @RequestParam(value = "name") String name, @Valid @RequestParam(value="student_id") String studentId) {

        Applicant applicantResult = this.applicantService.getApplicant(name, studentId);

        return applicantResult == null ?
                ApplicantResponse.builder()
                    .name(name)
                    .studentId(studentId)
                    .build()
                : ApplicantResponse.of(applicantResult);
    }

    @GetMapping("/apply/form")
    public ApplyFormResponse getApplyFormInfo(@Valid @RequestParam(value = "student_id") String studentId) {

        Apply applyResult = this.applyService.getApply(studentId);

        return applyResult == null ?
                ApplyFormResponse.builder()
                        .studentId(studentId)
                        .documentStatus(DocumentStatus.TEMPORARY.toString())
                        .build()
                : ApplyFormResponse.of(applyResult);
    }

    @PostMapping("/apply/form")
    public void postApplyInfo(@Valid @RequestBody ApplyForm applyForm) {

        String[] birth = applyForm.getBirth().split("\\.");

        Applicant applicant = this.applicantService.getApplicant(applyForm.getName(), applyForm.getStudentId()) == null ? Applicant.builder()
                .name(applyForm.getName())
                .birth(LocalDate.of(Integer.parseInt(birth[0]), Integer.parseInt(birth[1]), Integer.parseInt(birth[2])))
                .phoneNumber(applyForm.getPhoneNumber())
                .address(applyForm.getAddress())
                .studentId(applyForm.getStudentId())
                .build()
                : this.applicantService.getApplicant(applyForm.getName(), applyForm.getStudentId());

        List<ApplicantExperience> applicantExperiences = null;
        for (Experience experience : applyForm.getExperiences()) {
            ApplicantExperience applicantExperience = this.applicantExperienceService.getApplicantExperience(experience) == null ? ApplicantExperience.builder()
                    .experience(experience)
                    .build()
                    : this.applicantExperienceService.getApplicantExperience(experience);
            applicant.addApplicantExperience(applicantExperience);
            applicantExperiences.add(applicantExperience);
        }
        Apply apply = this.applyService.getApply(applyForm.getStudentId()) == null ? Apply.builder()
                .preferredInstrument(Instrument.valueOf(applyForm.getPreferredInstrument()))
                .introduction(applyForm.getIntroduction())
                .documentStatus(DocumentStatus.valueOf(applyForm.getDocumentStatus()))
                .build()
                : this.applyService.getApply(applyForm.getStudentId());

        this.applyService.addNewApply(apply, applicant, applicantExperiences);
    }

}