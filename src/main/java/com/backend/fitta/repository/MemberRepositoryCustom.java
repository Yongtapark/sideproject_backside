package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.MemberGymResponse;
import com.backend.fitta.dto.team.MemberTeamResponse;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamResponse> searchTeamMemberList(Long teamId);
    List<MemberGymResponse> searchGymMemberList(Long gymId);
}
