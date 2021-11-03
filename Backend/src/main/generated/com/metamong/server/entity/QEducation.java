package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEducation is a Querydsl query type for Education
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEducation extends EntityPathBase<Education> {

    private static final long serialVersionUID = -364195897L;

    public static final QEducation education1 = new QEducation("education1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Certificate, QCertificate> certificates = this.<Certificate, QCertificate>createList("certificates", Certificate.class, QCertificate.class, PathInits.DIRECT2);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final StringPath education = createString("education");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final ListPath<Mission, QMission> missions = this.<Mission, QMission>createList("missions", Mission.class, QMission.class, PathInits.DIRECT2);

    public QEducation(String variable) {
        super(Education.class, forVariable(variable));
    }

    public QEducation(Path<? extends Education> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEducation(PathMetadata metadata) {
        super(Education.class, metadata);
    }

}

