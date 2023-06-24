package com.backend.fitta.dto.ownermypage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerAllView {

    private MemberRate memberRate;
    private MemberTodayRate memberTodayRate;
    private AllGymCount allgymCount;
    private MemberAgeRate memberAgeRate;



}


