package com.messenger.messenger.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SelfDataDTO {
    private String country;
    private String city;
    private String dateOfBirth;
}
