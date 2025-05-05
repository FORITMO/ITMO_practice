package com.example.pet_shelter.model.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ShelterResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private List<Long> petIds;
    private int petsCount;
}