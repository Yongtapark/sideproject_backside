package com.backend.fitta.repository.owner;

import com.backend.fitta.dto.ownermypage.*;
import com.backend.fitta.entity.enums.Gender;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

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

    public OwnerAllView ownerAllView(Long ownerId){
        AllGymCount allgymCount = ownerAllGymInfoResponse(ownerId);
        MemberTodayRate memberTodayRate = calculateSignupToday(ownerId);
        MemberRate memberRate = calculateGenderRate(ownerId);
        MemberAgeRate memberAgeRate = calculateAgeRate(ownerId);
        return new OwnerAllView(memberRate, memberTodayRate, allgymCount,memberAgeRate);
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
    public AllGymCount ownerAllGymInfoResponse(Long ownerId){
        Long gymCount = GymCountByOwnerId(ownerId);
        Long staffCount = StaffCountByOwnerId(ownerId);
        Long memberCount = memberCountByOwnerId(ownerId);

       return new AllGymCount(gymCount,staffCount,memberCount);
    }


    //전체 오늘 가입자 수 계산
    public MemberTodayRate calculateSignupToday(Long ownerId){
        LocalDate today = LocalDate.now();

        Long maleCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.gender.eq(Gender.MALE))
                .where(member.gymJoinDate.eq(today))
                .fetchOne();

        Long femaleCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.gender.eq(Gender.FEMALE))
                .where(member.gymJoinDate.eq(today))
                .fetchOne();

        Long totalNewMembers = query.
                select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.gymJoinDate.eq(today))
                .fetchOne();

        double maleRate = ((double)maleCount/(double)totalNewMembers)*100;
        double femaleRate = ((double)femaleCount/(double)totalNewMembers)*100;

        return  new MemberTodayRate(maleRate,femaleRate,maleCount,femaleCount,totalNewMembers);
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

    //전체 비율 계산
    public MemberRate calculateGenderRate(Long ownerId){
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

        return new MemberRate(maleRate,femaleRate,maleCount,femaleCount,totalMembers);
    }

    /*전체 나이 비율 계산*/
    public MemberAgeRate calculateAgeRate(Long ownerId){
        LocalDate now = LocalDate.now();
        LocalDate tenYearsAgo = now.minusYears(10);
        LocalDate twentyYearsAgo = now.minusYears(20);
        LocalDate thirtyYearsAgo = now.minusYears(30);
        LocalDate fortyYearsAgo = now.minusYears(40);
        LocalDate fiftyYearsAgo = now.minusYears(50);
        LocalDate sixtyYearsAgo = now.minusYears(60);
        LocalDate seventyYearsAgo = now.minusYears(70);

        Long teensCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(twentyYearsAgo,tenYearsAgo))
                .fetchOne();

        Long twentiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(thirtyYearsAgo,twentyYearsAgo))
                .fetchOne();

        Long thirtiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(fortyYearsAgo,thirtyYearsAgo))
                .fetchOne();

        Long fortiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(fiftyYearsAgo,fortyYearsAgo))
                .fetchOne();

        Long fiftiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(sixtyYearsAgo,fiftyYearsAgo))
                .fetchOne();

        Long sixtiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.between(seventyYearsAgo,sixtyYearsAgo))
                .fetchOne();

        Long overSeventiesCount = query.select(member.count())
                .from(member)
                .where(member.gym.owner.id.eq(ownerId))
                .where(member.birthdate.before(seventyYearsAgo))
                .fetchOne();

        Long totalMembers = memberCountByOwnerId(ownerId);

        double teensRate = ((double)teensCount/(double)totalMembers)*100;
        double twentiesRate = ((double)twentiesCount/(double)totalMembers)*100;
        double thirtiesRate = ((double)thirtiesCount/(double)totalMembers)*100;
        double fortiesRate = ((double)fortiesCount/(double)totalMembers)*100;
        double fiftiesRate = ((double)fiftiesCount/(double)totalMembers)*100;
        double sixtiesRate = ((double)sixtiesCount/(double)totalMembers)*100;
        double overSeventiesRate = ((double)overSeventiesCount/(double)totalMembers)*100;



        return new MemberAgeRate(teensCount, twentiesCount,thirtiesCount,fortiesCount,fiftiesCount,sixtiesCount,overSeventiesCount,
                teensRate,twentiesRate,thirtiesRate,fortiesRate,fiftiesRate,sixtiesRate,overSeventiesRate);

    }




}
