package com.backend.fitta.dto.staff;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitta.dto.staff.QStaffTeamResponse is a Querydsl Projection type for StaffTeamResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStaffTeamResponse extends ConstructorExpression<StaffTeamResponse> {

    private static final long serialVersionUID = -653490870L;

    public QStaffTeamResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDate> birthdate, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Gender> gender, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<com.backend.fitta.entity.enums.Role> role) {
        super(StaffTeamResponse.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, com.backend.fitta.entity.enums.Gender.class, String.class, String.class, com.backend.fitta.entity.enums.Role.class}, id, name, birthdate, gender, phoneNumber, address, role);
    }

}

