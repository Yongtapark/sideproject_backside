package com.backend.fitta.dto.team;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitta.dto.team.QStaffTeamResponse is a Querydsl Projection type for StaffTeamResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStaffTeamResponse extends ConstructorExpression<StaffTeamResponse> {

    private static final long serialVersionUID = -1491892269L;

    public QStaffTeamResponse(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDate> birthday, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Gender> gender, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Grade> grade) {
        super(StaffTeamResponse.class, new Class<?>[]{String.class, java.time.LocalDate.class, com.backend.fitta.entity.enums.Gender.class, String.class, String.class, com.backend.fitta.entity.enums.Grade.class}, name, birthday, gender, phoneNumber, address, grade);
    }

}

