package com.example.ITMO_practice.model.dto.response;
import com.example.ITMO_practice.model.dto.request.CarInfoRequest;
import com.example.ITMO_practice.model.enums.Color;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarInfoResponse extends CarInfoRequest {
    private long carId;
    private UserInfoResponse user;

}
