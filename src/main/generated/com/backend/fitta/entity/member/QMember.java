package com.backend.fitta.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 2088785562L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.backend.fitta.entity.utils.QAuditing _super = new com.backend.fitta.entity.utils.QAuditing(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final DatePath<java.time.LocalDate> endSubscribeDate = createDate("endSubscribeDate", java.time.LocalDate.class);

    public final EnumPath<com.backend.fitta.entity.enums.Gender> gender = createEnum("gender", com.backend.fitta.entity.enums.Gender.class);

    public final com.backend.fitta.entity.gym.QGym gym;

    public final DatePath<java.time.LocalDate> gymJoinDate = createDate("gymJoinDate", java.time.LocalDate.class);

    public final NumberPath<Long> height = createNumber("height", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSubscribed = createBoolean("isSubscribed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath occupation = createString("occupation");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<com.backend.fitta.entity.gym.Registrations, com.backend.fitta.entity.gym.QRegistrations> registrations = this.<com.backend.fitta.entity.gym.Registrations, com.backend.fitta.entity.gym.QRegistrations>createList("registrations", com.backend.fitta.entity.gym.Registrations.class, com.backend.fitta.entity.gym.QRegistrations.class, PathInits.DIRECT2);

    public final EnumPath<com.backend.fitta.entity.enums.Role> role = createEnum("role", com.backend.fitta.entity.enums.Role.class);

    public final com.backend.fitta.entity.gym.QSchedule schedule;

    public final DatePath<java.time.LocalDate> subscribeDate = createDate("subscribeDate", java.time.LocalDate.class);

    public final com.backend.fitta.entity.gym.QTeam team;

    public final NumberPath<Long> weight = createNumber("weight", Long.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new com.backend.fitta.entity.gym.QGym(forProperty("gym"), inits.get("gym")) : null;
        this.schedule = inits.isInitialized("schedule") ? new com.backend.fitta.entity.gym.QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.team = inits.isInitialized("team") ? new com.backend.fitta.entity.gym.QTeam(forProperty("team"), inits.get("team")) : null;
    }

}

