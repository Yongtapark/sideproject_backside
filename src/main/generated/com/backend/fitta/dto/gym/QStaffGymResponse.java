package com.backend.fitta.dto.gym;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitta.dto.gym.QStaffGymResponse is a Querydsl Projection type for StaffGymResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStaffGymResponse extends ConstructorExpression<StaffGymResponse> {

    private static final long serialVersionUID = 824332821L;

    public QStaffGymResponse(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDate> birthdate, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Gender> gender, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> address) {
        super(StaffGymResponse.class, new Class<?>[]{String.class, java.time.LocalDate.class, com.backend.fitta.entity.enums.Gender.class, String.class, String.class}, name, birthdate, gender, phoneNumber, address);
    }

}

