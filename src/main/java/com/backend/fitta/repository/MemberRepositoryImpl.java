package com.backend.fitta.repository;

import com.backend.fitta.dto.team.MemberTeamResponse;
import com.backend.fitta.dto.team.QMemberTeamResponse;
import com.backend.fitta.dto.team.QStaffTeamResponse;
import com.backend.fitta.dto.team.StaffTeamResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QStaff.staff;
import static com.backend.fitta.entity.gym.QTeam.team;
import static com.backend.fitta.entity.member.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom, StaffRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamResponse> searchMemberList(Long teamId) {
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

    @Override
    public List<StaffTeamResponse> searchStaffList(Long teamId) {
        return queryFactory
                .select(new QStaffTeamResponse(
                        staff.name,
                        staff.birthday,
                        staff.gender,
                        staff.phoneNumber,
                        staff.address,
                        staff.grade))
                .from(staff)
                .join(staff.team, team)
                .where(staff.team.id.eq(teamId))
                .fetch();
    }
}
