package com.backend.fitta.repository.staff;


import com.backend.fitta.entity.staff.Staff;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.staff.QStaff.staff;


@Repository
public class StaffQueryRepository {
    private final JPAQueryFactory query;

    public StaffQueryRepository(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }


    public Page<Staff> findAll(StaffSearchCond cond, Pageable pageable) {
        QueryResults<Staff> results = query.select(staff)
                .from(staff)
                .where(likeStaffName(cond.getStaffName()),
                        gym.id.eq(cond.getGymId()))
                .orderBy(staff.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Staff> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }

    private BooleanExpression likeStaffName(String staffName) {
        if (StringUtils.hasText(staffName)) {
            return staff.name.like("%" + staffName + "%");
        }
        return null;
    }



}