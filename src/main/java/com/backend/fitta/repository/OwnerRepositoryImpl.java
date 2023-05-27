package com.backend.fitta.repository;

import com.backend.fitta.dto.owner.GymOwnerResponse;
import com.backend.fitta.dto.owner.QGymOwnerResponse;
import com.backend.fitta.entity.gym.QOwner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.backend.fitta.entity.gym.QGym.gym;
import static com.backend.fitta.entity.gym.QOwner.*;

public class OwnerRepositoryImpl implements OwnerRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public OwnerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<GymOwnerResponse> searchOwnerGymList(Long ownerId) {
        return queryFactory
                .select(new QGymOwnerResponse(
                        gym.name,
                        gym.phoneNumber,
                        gym.address,
                        gym.genderDivision))
                .from(gym)
                .join(gym.owner,owner)
                .where(gym.owner.id.eq(ownerId))
                .fetch();
    }
}
