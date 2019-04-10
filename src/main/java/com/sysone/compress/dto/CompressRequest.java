package com.sysone.compress.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompressRequest {
    @NotBlank(message = "field Value can't be empty")
    private String value;
}
