package com.backend.fitta.entity.gym;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegistrations is a Querydsl query type for Registrations
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegistrations extends EntityPathBase<Registrations> {

    private static final long serialVersionUID = -583653421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegistrations registrations = new QRegistrations("registrations");

    public final QClasses classes;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.fitta.entity.member.QMember member;

    public final DatePath<java.time.LocalDate> registDate = createDate("registDate", java.time.LocalDate.class);

    public QRegistrations(String variable) {
        this(Registrations.class, forVariable(variable), INITS);
    }

    public QRegistrations(Path<? extends Registrations> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegistrations(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegistrations(PathMetadata metadata, PathInits inits) {
        this(Registrations.class, metadata, inits);
    }

    public QRegistrations(Class<? extends Registrations> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classes = inits.isInitialized("classes") ? new QClasses(forProperty("classes"), inits.get("classes")) : null;
        this.member = inits.isInitialized("member") ? new com.backend.fitta.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

