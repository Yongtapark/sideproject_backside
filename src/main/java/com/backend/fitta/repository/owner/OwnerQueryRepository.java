package com.backend.fitta.repository.owner;

import com.backend.fitta.dto.gym.OwnerAllGymInfoResponse;
import com.backend.fitta.entity.gym.QStaff;
import com.backend.fitta.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QStaff.staff;
import static com.backend.fitta.entity.member.QMember.member;

@Repository
public class OwnerQueryRepository {
    private final JPAQueryFactory query;



    public OwnerQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
    //체육관 수
    public Long GymCountByOwnerId(Long ownerId){
        long count = query
                .selectFrom(gym)
                .where(gym.owner.id.eq(ownerId))
                .stream().count();

        return count;
    }
    //직원 총 수
    public Long StaffCountByOwnerId(Long OwnerId){
        long count = query
                .selectFrom(staff)
                .where(staff.gym.owner.id.eq(OwnerId))
                .stream().count();

        return count;
    }

    //회원 총 수
    public Long memberCountByOwnerId(Long OwnerId){
        long count = query.selectFrom(member)
                .where(member.gym.owner.id.eq(OwnerId))
                .stream().count();
        return count;
    }

    public OwnerAllGymInfoResponse ownerAllGymInfoResponse(Long ownerId){
        Long gymCount = GymCountByOwnerId(ownerId);
        Long staffCount = StaffCountByOwnerId(ownerId);
        Long memberCount = memberCountByOwnerId(ownerId);

       return new OwnerAllGymInfoResponse(gymCount,staffCount,memberCount);
    }




}
