package com.backend.fitta.repository.owner;

import com.backend.fitta.dto.gym.OwnerAllGymInfoResponse;
import com.backend.fitta.dto.common.GenderLate;
import com.backend.fitta.entity.enums.Gender;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QStaff.staff;
import static com.backend.fitta.entity.member.QMember.member;

@Repository
@Slf4j
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


    //전체 오늘 가입자 수 계산
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
    //전체 가입률 계산
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

    //전체 남녀비율 계산
    public GenderLate calculateGenderRate(Long ownerId){
        Long maleCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.gender.eq(Gender.MALE))
                .fetchOne();

        Long femaleCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.gender.eq(Gender.FEMALE))
                .fetchOne();

        Long totalMembers = memberCountByOwnerId(ownerId);


        double maleRate = ((double)maleCount/(double)totalMembers)*100;
        double femaleRate = ((double)femaleCount/(double)totalMembers)*100;

        return new GenderLate(maleRate,femaleRate,maleCount,femaleCount,totalMembers);
    }




}
