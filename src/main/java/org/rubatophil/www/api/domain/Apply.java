package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.DocumentStatus;
import org.rubatophil.www.api.domain.type.Instrument;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLY")
@Getter @Setter
public class Apply {

    @Id @GeneratedValue
    @Column(name = "apply_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Applicant applicant;

    @OneToOne
    @JoinColumn(name = "apply_campaign_id")
    @NotNull
    private ApplyCampaign applyCampaign;

    @NotNull
    private Instrument preferredInstrument;
    @NotNull
    private String Introduction;

    @NotNull
    private DocumentStatus documentStatus;

    @NotNull
    private Boolean isApproved;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    @PrePersist
    public void PrePersist() {
        this.documentStatus = this.documentStatus == null ? DocumentStatus.TEMPORARY : this.documentStatus;
        this.isApproved = this.isApproved == null ? Boolean.FALSE : this.isApproved;
    }
}
