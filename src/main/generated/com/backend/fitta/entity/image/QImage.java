package com.backend.fitta.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = 442415092L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImage image = new QImage("image");

    public final com.backend.fitta.entity.utils.QAuditing _super = new com.backend.fitta.entity.utils.QAuditing(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.backend.fitta.entity.gym.QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath originalName = createString("originalName");

    public final StringPath storeName = createString("storeName");

    public QImage(String variable) {
        this(Image.class, forVariable(variable), INITS);
    }

    public QImage(Path<? extends Image> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImage(PathMetadata metadata, PathInits inits) {
        this(Image.class, metadata, inits);
    }

    public QImage(Class<? extends Image> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new com.backend.fitta.entity.gym.QGym(forProperty("gym"), inits.get("gym")) : null;
    }

}

