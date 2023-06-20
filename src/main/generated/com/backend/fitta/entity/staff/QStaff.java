package com.backend.fitta.entity.staff;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = 1726280510L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaff staff = new QStaff("staff");

    public final com.backend.fitta.entity.utils.QAuditing _super = new com.backend.fitta.entity.utils.QAuditing(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.backend.fitta.entity.enums.Gender> gender = createEnum("gender", com.backend.fitta.entity.enums.Gender.class);

    public final com.backend.fitta.entity.gym.QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<com.backend.fitta.entity.enums.Role> role = createEnum("role", com.backend.fitta.entity.enums.Role.class);

    public final com.backend.fitta.entity.gym.QSchedule schedule;

    public final com.backend.fitta.entity.gym.QTeam team;

    public QStaff(String variable) {
        this(Staff.class, forVariable(variable), INITS);
    }

    public QStaff(Path<? extends Staff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStaff(PathMetadata metadata, PathInits inits) {
        this(Staff.class, metadata, inits);
    }

    public QStaff(Class<? extends Staff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new com.backend.fitta.entity.gym.QGym(forProperty("gym"), inits.get("gym")) : null;
        this.schedule = inits.isInitialized("schedule") ? new com.backend.fitta.entity.gym.QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.team = inits.isInitialized("team") ? new com.backend.fitta.entity.gym.QTeam(forProperty("team"), inits.get("team")) : null;
    }

}

