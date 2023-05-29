package com.backend.fitta.dto.owner;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitta.dto.owner.QGymOwnerResponse is a Querydsl Projection type for GymOwnerResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGymOwnerResponse extends ConstructorExpression<GymOwnerResponse> {

    private static final long serialVersionUID = 1381543066L;

    public QGymOwnerResponse(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.GenderDivision> genderDivision) {
        super(GymOwnerResponse.class, new Class<?>[]{String.class, String.class, String.class, com.backend.fitta.entity.enums.GenderDivision.class}, name, phoneNumber, address, genderDivision);
    }

}

