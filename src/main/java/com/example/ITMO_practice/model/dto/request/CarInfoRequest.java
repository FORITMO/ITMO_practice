package com.example.ITMO_practice.model.dto.request;


import com.example.ITMO_practice.model.enums.CarType;
import com.example.ITMO_practice.model.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.awt.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoRequest {
    private String brand;
    private String model;
    private Color color;
    private Integer year;
    private Long price;
    private Boolean isNew;
    private CarType type;
}
