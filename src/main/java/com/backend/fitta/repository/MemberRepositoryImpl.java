package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.MemberGymResponse;
import com.backend.fitta.dto.gym.QMemberGymResponse;
import com.backend.fitta.dto.team.MemberTeamResponse;
import com.backend.fitta.dto.team.QMemberTeamResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QTeam.team;
import static com.backend.fitta.entity.member.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamResponse> searchTeamMemberList(Long teamId) {
        return null;
    }

    @Override
    public List<MemberGymResponse> searchGymMemberList(Long gymId) {
        return queryFactory
                .select(new QMemberGymResponse(
                        member.email,
                        member.name,
                        member.birthday,
                        member.phoneNumber,
                        member.address,
                        member.gender))
                .from(member)
                .join(member.gym, gym).fetchJoin()
                .where(member.gym.id.eq(gymId))
                .fetch();
    }

}
