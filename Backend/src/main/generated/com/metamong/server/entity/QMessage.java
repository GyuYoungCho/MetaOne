package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -1556880410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessage message = new QMessage("message");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final NumberPath<Integer> isRead = createNumber("isRead", Integer.class);

    public final QUser recvUserId;

    public final QUser sentUserId;

    public final StringPath title = createString("title");

    public QMessage(String variable) {
        this(Message.class, forVariable(variable), INITS);
    }

    public QMessage(Path<? extends Message> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessage(PathMetadata metadata, PathInits inits) {
        this(Message.class, metadata, inits);
    }

    public QMessage(Class<? extends Message> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recvUserId = inits.isInitialized("recvUserId") ? new QUser(forProperty("recvUserId"), inits.get("recvUserId")) : null;
        this.sentUserId = inits.isInitialized("sentUserId") ? new QUser(forProperty("sentUserId"), inits.get("sentUserId")) : null;
    }

}

