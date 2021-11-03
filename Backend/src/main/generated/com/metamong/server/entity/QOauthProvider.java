package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOauthProvider is a Querydsl query type for OauthProvider
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOauthProvider extends EntityPathBase<OauthProvider> {

    private static final long serialVersionUID = -417844665L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOauthProvider oauthProvider = new QOauthProvider("oauthProvider");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath providerName = createString("providerName");

    public final QUser user;

    public QOauthProvider(String variable) {
        this(OauthProvider.class, forVariable(variable), INITS);
    }

    public QOauthProvider(Path<? extends OauthProvider> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOauthProvider(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOauthProvider(PathMetadata metadata, PathInits inits) {
        this(OauthProvider.class, metadata, inits);
    }

    public QOauthProvider(Class<? extends OauthProvider> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

