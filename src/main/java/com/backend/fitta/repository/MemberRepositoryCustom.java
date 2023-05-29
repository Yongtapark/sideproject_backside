package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.MemberGymResponse;
import com.backend.fitta.dto.team.SimpleMemberInfo;

import java.util.List;

public interface MemberRepositoryCustom {
    List<SimpleMemberInfo> searchTeamMemberList(Long teamId);
    List<MemberGymResponse> searchGymMemberList(Long gymId);
}
