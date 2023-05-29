package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.MemberGymResponse;
import com.backend.fitta.dto.gym.QMemberGymResponse;
import com.backend.fitta.dto.gym.QStaffGymResponse;
import com.backend.fitta.dto.gym.StaffGymResponse;
import com.backend.fitta.dto.team.SimpleMemberInfo;
import com.backend.fitta.dto.team.QStaffTeamResponse;
import com.backend.fitta.dto.team.StaffTeamResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QStaff.staff;
import static com.backend.fitta.entity.gym.QTeam.team;
import static com.backend.fitta.entity.member.QMember.member;

public class GymRepositoryImpl implements MemberRepositoryCustom, StaffRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public GymRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<SimpleMemberInfo> searchTeamMemberList(Long teamId) {
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
                .join(member.gym, gym)
                .where(member.gym.id.eq(gymId))
                .fetch();
    }

    @Override
    public List<StaffTeamResponse> searchTeamStaffList(Long teamId) {
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

    @Override
    public List<StaffGymResponse> searchGymStaffList(Long gymId) {
        return queryFactory
                .select(new QStaffGymResponse(
                        staff.name,
                        staff.birthday,
                        staff.gender,
                        staff.phoneNumber,
                        staff.address,
                        staff.grade))
                .from(staff)
                .join(staff.gym, gym)
                .where(staff.gym.id.eq(gymId))
                .fetch();
    }
}
