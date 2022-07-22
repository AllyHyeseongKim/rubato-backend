package org.rubatophil.www.api.response.apply;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyDurationResponse {
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;

    @Builder
    public ApplyDurationResponse(LocalDateTime start, LocalDateTime end, String status) {
        this.start = start;
        this.end = end;
        this.status = status;
    }
}
