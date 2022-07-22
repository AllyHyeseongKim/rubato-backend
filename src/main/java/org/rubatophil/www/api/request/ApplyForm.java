package org.rubatophil.www.api.request;

import lombok.*;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Experience;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyForm {

    private String name;
    private String college;
    private String school;
    private String department;
    @NotNull
    private String studentId;
    private String birth;
    private String phoneNumber;
    private Address address;
    private String introduction;
    private List<Experience> experiences;
    private String preferredInstrument;
    @NotNull
    private String documentStatus;

    @Builder
    public ApplyForm(String name, String college, String school, String department, String studentId, String birth, String phoneNumber, Address address, String introduction, List<Experience> experiences, String preferredInstrument, String documentStatus) {
        this.name = name;
        this.college = college;
        this.school = school;
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
}
