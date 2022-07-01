package org.rubatophil.www.api.domain.mapping;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_INSTRUMENT")
@Getter @Setter
public class MemberInstrument {

    @Id @GeneratedValue
    @Column(name = "member_instrument_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
