package com.backend.fitta.repository.owner;

import com.backend.fitta.dto.gym.OwnerAllGymInfoResponse;
import com.backend.fitta.entity.gym.QStaff;
import com.backend.fitta.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
                .select(gym.count())
                .from(gym)
                .where(gym.owner.id.eq(ownerId))
                .fetchOne();

        return count;
    }
    //직원 총 수
    public Long StaffCountByOwnerId(Long OwnerId){
        long count = query
                .select(staff.count())
                .from(staff)
                .where(staff.gym.owner.id.eq(OwnerId))
                .fetchOne();

        return count;
    }

    //회원 총 수
    public Long memberCountByOwnerId(Long OwnerId){
        long count = query
                .select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(OwnerId))
                .fetchOne();
        return count;
    }
    /*체육관,직원,회원 수 반환*/
    public OwnerAllGymInfoResponse ownerAllGymInfoResponse(Long ownerId){
        Long gymCount = GymCountByOwnerId(ownerId);
        Long staffCount = StaffCountByOwnerId(ownerId);
        Long memberCount = memberCountByOwnerId(ownerId);

       return new OwnerAllGymInfoResponse(gymCount,staffCount,memberCount);
    }


    //오늘 가입자 수 계산
    public Long calculateSignupToday(Long ownerId){
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().plusDays(1).atStartOfDay();
        Long newMembers = query.
                select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.createdDate.between(startOfDay,endOfDay))
                .fetchOne();

        return newMembers;
    }
    //가입률 계산
    public double calculateSignupRate(Long ownerId){
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        Long newMembers = query.
                select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.createdDate.after(oneMonthAgo.atStartOfDay()))
                .fetchOne();

        Long totalMembers = memberCountByOwnerId(ownerId);

        return (double)newMembers/totalMembers;
    }




}
