package com.backend.fitta.entity.gym;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgram is a Querydsl query type for Program
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProgram extends EntityPathBase<Program> {

    private static final long serialVersionUID = 1431602749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProgram program = new QProgram("program");

    public final QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final ListPath<Registrations, QRegistrations> registrations = this.<Registrations, QRegistrations>createList("registrations", Registrations.class, QRegistrations.class, PathInits.DIRECT2);

    public QProgram(String variable) {
        this(Program.class, forVariable(variable), INITS);
    }

    public QProgram(Path<? extends Program> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProgram(PathMetadata metadata, PathInits inits) {
        this(Program.class, metadata, inits);
    }

    public QProgram(Class<? extends Program> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new QGym(forProperty("gym"), inits.get("gym")) : null;
    }

}

