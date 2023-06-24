package com.backend.fitta.dto.gym;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitta.dto.gym.QMemberGymResponse is a Querydsl Projection type for MemberGymResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberGymResponse extends ConstructorExpression<MemberGymResponse> {

    private static final long serialVersionUID = 1336139081L;

    public QMemberGymResponse(com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDate> birthdate, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Gender> gender, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Role> role) {
        super(MemberGymResponse.class, new Class<?>[]{String.class, String.class, java.time.LocalDate.class, String.class, String.class, com.backend.fitta.entity.enums.Gender.class, com.backend.fitta.entity.enums.Role.class}, email, name, birthdate, phoneNumber, address, gender, role);
    }

}

