package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.QStaffGymResponse;
import com.backend.fitta.dto.gym.StaffGymResponse;
import com.backend.fitta.dto.team.QStaffTeamResponse;
import com.backend.fitta.dto.team.StaffTeamResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QStaff.staff;
import static com.backend.fitta.entity.gym.QTeam.team;

public class StaffRepositoryImpl implements StaffRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StaffRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
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
