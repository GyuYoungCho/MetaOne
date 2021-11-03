package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCertificate is a Querydsl query type for Certificate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCertificate extends EntityPathBase<Certificate> {

    private static final long serialVersionUID = 96804342L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCertificate certificate = new QCertificate("certificate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    public final QEducation education;

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final BooleanPath isAuthenticated = createBoolean("isAuthenticated");

    public final BooleanPath isEducated = createBoolean("isEducated");

    public final NumberPath<Integer> passTime = createNumber("passTime", Integer.class);

    public final QUser user;

    public QCertificate(String variable) {
        this(Certificate.class, forVariable(variable), INITS);
    }

    public QCertificate(Path<? extends Certificate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCertificate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCertificate(PathMetadata metadata, PathInits inits) {
        this(Certificate.class, metadata, inits);
    }

    public QCertificate(Class<? extends Certificate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.education = inits.isInitialized("education") ? new QEducation(forProperty("education")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

