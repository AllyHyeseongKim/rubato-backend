package org.rubatophil.www.api.domain.type;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Experience {

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;
    @NotNull
    private Integer experienceYear;

    @Builder
    public Experience(Instrument instrument, Integer experienceYear) {
        this.instrument = instrument;
        this.experienceYear = experienceYear;
    }
}
