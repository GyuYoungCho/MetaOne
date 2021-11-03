package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuestBook is a Querydsl query type for GuestBook
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGuestBook extends EntityPathBase<GuestBook> {

    private static final long serialVersionUID = -962160384L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuestBook guestBook = new QGuestBook("guestBook");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final QUser user;

    public QGuestBook(String variable) {
        this(GuestBook.class, forVariable(variable), INITS);
    }

    public QGuestBook(Path<? extends GuestBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuestBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuestBook(PathMetadata metadata, PathInits inits) {
        this(GuestBook.class, metadata, inits);
    }

    public QGuestBook(Class<? extends GuestBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

