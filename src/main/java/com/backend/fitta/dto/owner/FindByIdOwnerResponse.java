package com.backend.fitta.dto.owner;

import com.backend.fitta.dto.gym.BasicGymInfoResponse;
import lombok.Data;

import java.util.List;

@Data
public class FindByIdOwnerResponse {
    String name;
    String phoneNumber;
    List<BasicGymInfoResponse> gymList;
}
