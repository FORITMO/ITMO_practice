package com.example.pet_shelter.model.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ShelterResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private List<Long> petIds; // только ID питомцев
}