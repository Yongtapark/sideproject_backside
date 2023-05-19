package com.backend.fitta.repository;

import com.backend.fitta.dto.team.MemberTeamResponse;
import com.backend.fitta.dto.team.QMemberTeamResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QTeam.team;
import static com.backend.fitta.entity.member.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamResponse> search(Long teamId) {
        return queryFactory
                .select(new QMemberTeamResponse(
                        member.email,
                        member.name,
                        member.birthday,
                        member.phoneNumber,
                        member.address,
                        member.gender))
                .from(member)
                .join(member.team, team)
                .where(member.team.id.eq(teamId))
                .fetch();
    }
}
