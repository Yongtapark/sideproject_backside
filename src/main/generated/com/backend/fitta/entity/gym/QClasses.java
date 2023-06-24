package com.backend.fitta.entity.gym;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClasses is a Querydsl query type for Classes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClasses extends EntityPathBase<Classes> {

    private static final long serialVersionUID = -1700356129L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClasses classes = new QClasses("classes");

    public final QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final ListPath<Registrations, QRegistrations> registrations = this.<Registrations, QRegistrations>createList("registrations", Registrations.class, QRegistrations.class, PathInits.DIRECT2);

    public QClasses(String variable) {
        this(Classes.class, forVariable(variable), INITS);
    }

    public QClasses(Path<? extends Classes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClasses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClasses(PathMetadata metadata, PathInits inits) {
        this(Classes.class, metadata, inits);
    }

    public QClasses(Class<? extends Classes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new QGym(forProperty("gym"), inits.get("gym")) : null;
    }

}

