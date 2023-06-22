package com.backend.fitta.repository.gym;

import com.backend.fitta.entity.gym.Gym;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;

@Repository
public class GymQueryRepository {

    private final JPAQueryFactory query;

    public GymQueryRepository(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }


    public Page<Gym> findAll(GymSearchCond cond, Pageable pageable) {
        QueryResults<Gym> results = query.select(gym)
                .from(gym)
                .where(likeGymName(cond.getGymName()))
                .orderBy(gym.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Gym> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }

    private BooleanExpression likeGymName(String gymName) {
        if (StringUtils.hasText(gymName)) {
            return gym.name.like("%" + gymName + "%");
        }
        return null;
    }
}
