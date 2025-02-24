package com.example.ITMO_practice.model.dto.response;

import com.example.ITMO_practice.model.dto.request.UserInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse extends UserInfoRequest {
    private long id;
}
