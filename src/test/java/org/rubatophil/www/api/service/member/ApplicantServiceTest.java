package org.rubatophil.www.api.service.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Experience;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.repository.member.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(ApplicantService.class)
public class ApplicantServiceTest {

    @Autowired
    ApplicantService applicantService;

    @MockBean
    ApplicantRepository applicantRepository;

    Address address;
    Department department;
    Applicant applicant;
    ApplicantExperience applicantExperience;

    @BeforeEach
    void setUp() {

        this.applicantExperience = ApplicantExperience.builder()
                .experience(Experience.builder()
                        .instrument(Instrument.VIOLIN)
                        .experienceYear(10)
                        .build()
                )
                .build();

        this.address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        this.applicant = Applicant.builder()
                .name("test applicant name")
                .birth(LocalDate.of(1999, 1, 1))
                .phoneNumber("01000000000")
                .address(this.address)
                .studentId("20180000")
                .build();
        this.applicant.addApplicantExperience(this.applicantExperience);

        this.department = Department.builder()
                .college("창의ICT공과대학")
                .school("School of Computer Science and Engineering")
                .build();
        this.department.addApplicant(this.applicant);
    }

    @Test
    @DisplayName("getApplicant")
    public void getApplicantTest() {

        //given
        //when
        when(this.applicantRepository.findByNameAndStudentId(this.applicant.getName(), this.applicant.getStudentId())).thenReturn(this.applicant);

        //then
        assertEquals(this.applicant, this.applicantService.getApplicant(this.applicant.getName(), this.applicant.getStudentId()));
    }
}
