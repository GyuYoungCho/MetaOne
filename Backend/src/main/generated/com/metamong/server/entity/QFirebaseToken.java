package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFirebaseToken is a Querydsl query type for FirebaseToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFirebaseToken extends EntityPathBase<FirebaseToken> {

    private static final long serialVersionUID = 1096893649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFirebaseToken firebaseToken = new QFirebaseToken("firebaseToken");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath token = createString("token");

    public final QUser user;

    public QFirebaseToken(String variable) {
        this(FirebaseToken.class, forVariable(variable), INITS);
    }

    public QFirebaseToken(Path<? extends FirebaseToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFirebaseToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFirebaseToken(PathMetadata metadata, PathInits inits) {
        this(FirebaseToken.class, metadata, inits);
    }

    public QFirebaseToken(Class<? extends FirebaseToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

