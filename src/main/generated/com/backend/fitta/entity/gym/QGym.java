package com.backend.fitta.entity.gym;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGym is a Querydsl query type for Gym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGym extends EntityPathBase<Gym> {

    private static final long serialVersionUID = 77184500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGym gym = new QGym("gym");

    public final com.backend.fitta.entity.QAuditing _super = new com.backend.fitta.entity.QAuditing(this);

    public final StringPath address = createString("address");

    public final StringPath businessIdentificationNumber = createString("businessIdentificationNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.backend.fitta.entity.enums.GenderDivision> genderDivision = createEnum("genderDivision", com.backend.fitta.entity.enums.GenderDivision.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<com.backend.fitta.entity.member.Member, com.backend.fitta.entity.member.QMember> member = this.<com.backend.fitta.entity.member.Member, com.backend.fitta.entity.member.QMember>createList("member", com.backend.fitta.entity.member.Member.class, com.backend.fitta.entity.member.QMember.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QOwner owner;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<Staff, QStaff> staff = this.<Staff, QStaff>createList("staff", Staff.class, QStaff.class, PathInits.DIRECT2);

    public QGym(String variable) {
        this(Gym.class, forVariable(variable), INITS);
    }

    public QGym(Path<? extends Gym> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGym(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGym(PathMetadata metadata, PathInits inits) {
        this(Gym.class, metadata, inits);
    }

    public QGym(Class<? extends Gym> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
    }

}

