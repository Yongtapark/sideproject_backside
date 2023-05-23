package com.backend.fitta.repository;

import com.backend.fitta.dto.team.MemberTeamResponse;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamResponse> searchMemberList(Long teamId);
}
