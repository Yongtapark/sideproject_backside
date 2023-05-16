package com.backend.fitta.entity.gym;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -1901879996L;

    public static final QTeam team = new QTeam("team");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.backend.fitta.entity.member.Member, com.backend.fitta.entity.member.QMember> members = this.<com.backend.fitta.entity.member.Member, com.backend.fitta.entity.member.QMember>createList("members", com.backend.fitta.entity.member.Member.class, com.backend.fitta.entity.member.QMember.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<Staff, QStaff> staffs = this.<Staff, QStaff>createList("staffs", Staff.class, QStaff.class, PathInits.DIRECT2);

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}

