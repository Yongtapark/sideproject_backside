package com.backend.fitta.repository.gym;

import com.backend.fitta.entity.gym.QGym;
import com.backend.fitta.entity.gym.QOwner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QOwner.*;

@Repository
public class GymQueryRepository {
    private final JPAQueryFactory query;



    public GymQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Long GymCountByOwnerId(Long ownerId){
        long count = query
                .selectFrom(gym)
                .where(gym.owner.id.eq(ownerId))
                .stream().count();

        return count;
    }




}
