package com.kakao.membership.controller.dto.membership;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointChangeRequest {

    @NotNull
    private Long storeId;

    @Pattern(regexp = "^[0-9]{10}", message = "point.change.barcode.invalid")
    private String barcode;

    @NotNull
    private Long amount;
}
