package com.backend.fitta.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditing is a Querydsl query type for Auditing
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditing extends EntityPathBase<Auditing> {

    private static final long serialVersionUID = 1133539515L;

    public static final QAuditing auditing = new QAuditing("auditing");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public QAuditing(String variable) {
        super(Auditing.class, forVariable(variable));
    }

    public QAuditing(Path<? extends Auditing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditing(PathMetadata metadata) {
        super(Auditing.class, metadata);
    }

}

